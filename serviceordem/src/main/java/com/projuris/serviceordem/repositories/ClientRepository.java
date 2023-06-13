package com.projuris.serviceordem.repositories;


import com.projuris.serviceordem.models.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<ClientModel, UUID>, JpaSpecificationExecutor <ClientModel>{
    boolean existsByClientEmail(String clientEmail);
    boolean existsByClientId(UUID clientId);

}
