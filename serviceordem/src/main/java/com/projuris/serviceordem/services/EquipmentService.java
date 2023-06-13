package com.projuris.serviceordem.services;

import com.projuris.serviceordem.models.EquipmentModel;
import com.projuris.serviceordem.specifications.SpecificationTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface EquipmentService {
    EquipmentModel save(EquipmentModel equipmentModel);

    Optional<EquipmentModel> findById(UUID equipmentId);

    Page<EquipmentModel> findAll(SpecificationTemplate.EquipmentSpec spec, Pageable pageable);

    boolean existsByEquipmentId(UUID soEquipments);
}
