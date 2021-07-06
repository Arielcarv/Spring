package br.com.estagiotechb2w.service.interfaces;

import br.com.estagiotechb2w.dto.AddressDTO;
import br.com.estagiotechb2w.entity.Address;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

public interface AddressService {
    Address createAddress(AddressDTO addressDTO);

    Page<Address> findAll(Pageable pageable);

    Address findById(Long Id);

    Address updateAddress(Long id, AddressDTO addressDTO);

    String deleteAddress(Long id);
}
