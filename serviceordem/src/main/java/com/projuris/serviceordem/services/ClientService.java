package com.projuris.serviceordem.services;

import com.projuris.serviceordem.models.ClientModel;
import com.projuris.serviceordem.specifications.SpecificationTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ClientService {
    void save(ClientModel clientModel);

    boolean existsByClientEmail(String clientEmail);

    Page<ClientModel> findAll(SpecificationTemplate.ClientSpec spec, Pageable pageable);

    Optional<ClientModel> findById(UUID clientId);

    boolean existsByClientId(UUID soClients);
}
