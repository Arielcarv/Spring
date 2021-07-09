package br.com.estagiotechb2w.service.interfaces;

import br.com.estagiotechb2w.dto.AddressDTO;
import br.com.estagiotechb2w.entity.Address;
import br.com.estagiotechb2w.repository.AddressRepository;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@Service
public class AddressServiceImpl implements AddressService {
    // Dependency injection by constructor
    private final AddressRepository addressRepository;

    // Instanciate CEP query url
    private static final String BASE_URL = "https://viacep.com.br/ws/";

    public AddressServiceImpl(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    @Override
    public Address createAddress(AddressDTO addressDTO) {

        Address address = new Address(addressDTO);
        addressRepository.save(address);
        return address;
    }

    @Override
    public Page<Address> findAll(Pageable pageable) {
        return addressRepository.findAll(pageable);
    }

    @Override
    public Address findById(Long id) {
        return getAddress(id);
    }

    @Override
    public Address updateAddress(Long id, AddressDTO addressDTO) {

        Address address = getAddress(id);
        address.setBairro(addressDTO.getBairro());
        address.setCep(addressDTO.getCep());
        address.setComplemento(addressDTO.getComplemento());
        address.setLocalidade(addressDTO.getLocalidade());
        address.setLogradouro(addressDTO.getLogradouro());
        address.setUf(addressDTO.getUf());
        addressRepository.save(address);
        return address;
    }

    @Override
    public String deleteAddress(Long id) {
        Address address = getAddress(id);
        addressRepository.delete(address);
        return "deleted";
    }

    @Override
    public AddressDTO consumedAPI(String cep) {
        cep = getDigits(cep);
        RestTemplate restTemplate = new RestTemplate();
        AddressDTO response = null;
        try {
            String url = BASE_URL + cep + "/json";
            response = restTemplate.getForObject(url, AddressDTO.class);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "SIT UP AND CRY");
        }

        if (response.getCep() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND");
        }

        return response;
    }

    private String getDigits(String str) {
        // Replace all space digits to nothing
        return str.replaceAll("\\D+", "");
    }

    private Address getAddress(Long id) {
        Optional<Address> address = addressRepository.findById(id);

        if (address.isPresent()) {
            return address.get();
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }
}
