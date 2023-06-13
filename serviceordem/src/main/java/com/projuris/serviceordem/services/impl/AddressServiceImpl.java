package com.projuris.serviceordem.services.impl;

import com.projuris.serviceordem.models.AddressModel;
import com.projuris.serviceordem.repositories.AddressRepository;
import com.projuris.serviceordem.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;


    @Override
    public void save(AddressModel addressModel) {
        addressRepository.save(addressModel);
    }
}
