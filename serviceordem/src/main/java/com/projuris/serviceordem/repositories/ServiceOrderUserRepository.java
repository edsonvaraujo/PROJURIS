package com.projuris.serviceordem.repositories;

import com.projuris.serviceordem.models.ServiceOrderUserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ServiceOrderUserRepository extends JpaRepository<ServiceOrderUserModel, UUID>, JpaSpecificationExecutor<ServiceOrderUserModel> {
}
