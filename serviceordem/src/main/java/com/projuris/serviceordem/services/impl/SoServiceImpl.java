package com.projuris.serviceordem.services.impl;

import com.projuris.serviceordem.models.SoModel;
import com.projuris.serviceordem.repositories.SoRepository;
import com.projuris.serviceordem.services.SoService;
import com.projuris.serviceordem.specifications.SpecificationTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SoServiceImpl implements SoService {

    @Autowired
    SoRepository soRepository;

    @Override
    public void save(SoModel soModel) {
        soRepository.save(soModel);
    }
    @Override
    public Long getMaxSoNumber() {
        return soRepository.getMaxSoNumber();
    }
    @Override
    public Page<SoModel> findAll(SpecificationTemplate.SoSpec spec, Pageable pageable) {
        return soRepository.findAll(spec, pageable);
    }

    @Override
    public Optional<SoModel> findById(UUID soId) {
        return soRepository.findById(soId);
    }

}
