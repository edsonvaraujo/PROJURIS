package com.projuris.serviceordem.services.impl;

import com.projuris.serviceordem.models.ClientModel;
import com.projuris.serviceordem.repositories.ClientRepository;
import com.projuris.serviceordem.services.ClientService;
import com.projuris.serviceordem.specifications.SpecificationTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;
    @Override
    public void save(ClientModel clientModel) {
        clientRepository.save(clientModel);
    }
    @Override
    public boolean existsByClientEmail(String clientEmail) {
        return clientRepository.existsByClientEmail(clientEmail);
    }

    @Override
    public Page<ClientModel> findAll(SpecificationTemplate.ClientSpec spec, Pageable pageable) {
        return clientRepository.findAll(spec, pageable);
    }

    @Override
    public Optional<ClientModel> findById(UUID clientId) {
        return clientRepository.findById(clientId);
    }

    @Override
    public boolean existsByClientId(UUID soClients) {
        return clientRepository.existsById(soClients);
    }
}
