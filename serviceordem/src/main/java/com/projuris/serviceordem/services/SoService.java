package com.projuris.serviceordem.services;

import com.projuris.serviceordem.models.SoModel;
import com.projuris.serviceordem.specifications.SpecificationTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface SoService {

    void save(SoModel soModel);

    Long getMaxSoNumber();

    Page<SoModel> findAll(SpecificationTemplate.SoSpec spec, Pageable pageable);

    Optional<SoModel> findById(UUID soId);
}
