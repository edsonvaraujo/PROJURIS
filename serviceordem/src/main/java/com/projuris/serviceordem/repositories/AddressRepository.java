package com.projuris.serviceordem.repositories;

import com.projuris.serviceordem.models.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface AddressRepository  extends JpaRepository<AddressModel, UUID>, JpaSpecificationExecutor<AddressModel> {
}
