package br.com.estagiotechb2w.controller;

import br.com.estagiotechb2w.dto.AddressDTO;
import br.com.estagiotechb2w.entity.Address;
import br.com.estagiotechb2w.service.interfaces.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("addresses")
    public ResponseEntity<?> createAddress(@RequestBody AddressDTO addressDTO){
        return ResponseEntity.ok().body(addressService.createAddress(addressDTO));
    }

    @GetMapping("addresses")
    public ResponseEntity<?> findAll(@RequestParam(defaultValue = "0") Integer page,
                                     @RequestParam(defaultValue = "10") Integer limit,
                                     @RequestParam(defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of(page,limit, Sort.by(sortBy));

        return ResponseEntity.ok().body(addressService.findAll(pageable));
    }

    @GetMapping("addresses/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(addressService.findById(id));
    }

    @PutMapping("addresses/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable Long id,
                                           @RequestBody AddressDTO addressDTO) {
        return ResponseEntity.ok().body(addressService.updateAddress(id, addressDTO));
    }

    @DeleteMapping("addresses/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable Long id) {
        return ResponseEntity.ok().body(addressService.deleteAddress(id));
    }
}
