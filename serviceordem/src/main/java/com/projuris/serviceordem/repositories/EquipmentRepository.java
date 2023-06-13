package com.projuris.serviceordem.repositories;

import com.projuris.serviceordem.models.EquipmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface EquipmentRepository extends JpaRepository<EquipmentModel, UUID>, JpaSpecificationExecutor<EquipmentModel>{
    boolean existsByEquipmentId(UUID equipId);
}
