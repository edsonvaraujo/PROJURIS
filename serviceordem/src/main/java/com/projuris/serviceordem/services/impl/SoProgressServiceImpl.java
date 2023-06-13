package com.projuris.serviceordem.services.impl;

import com.projuris.serviceordem.models.SoProgressModel;
import com.projuris.serviceordem.repositories.SoProgressRepository;
import com.projuris.serviceordem.services.SoProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SoProgressServiceImpl implements SoProgressService {

    @Autowired
    SoProgressRepository soProgressRepository;

    @Override
    public void save(SoProgressModel soProgress) {
        soProgressRepository.save(soProgress);
    }

    @Override
    public List<SoProgressModel> getAllSoSoProgress(UUID soId) {
        return soProgressRepository.FindAllSoSoProgress(soId);
    }
}
