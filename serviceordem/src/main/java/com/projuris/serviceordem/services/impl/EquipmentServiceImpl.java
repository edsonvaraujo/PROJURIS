package com.projuris.serviceordem.services.impl;

import com.projuris.serviceordem.models.EquipmentModel;
import com.projuris.serviceordem.repositories.EquipmentRepository;
import com.projuris.serviceordem.services.EquipmentService;
import com.projuris.serviceordem.specifications.SpecificationTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    EquipmentRepository equipmentRepository;
    @Override
    public EquipmentModel save(EquipmentModel equipmentModel) {
        return equipmentRepository.save(equipmentModel);
    }
    @Override
    public Optional<EquipmentModel> findById(UUID equipmentId) {
        return equipmentRepository.findById(equipmentId);
    }

    @Override
    public Page<EquipmentModel> findAll(SpecificationTemplate.EquipmentSpec spec, Pageable pageable) {
        return  equipmentRepository.findAll(spec, pageable);
    }

    @Override
    public boolean existsByEquipmentId(UUID soEquipments) {
        return equipmentRepository.existsByEquipmentId(soEquipments);
    }
}
