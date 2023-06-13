package com.projuris.serviceordem.services;

import com.projuris.serviceordem.models.SoProgressModel;
import com.projuris.serviceordem.specifications.SpecificationTemplate;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface SoProgressService {
    void save(SoProgressModel soProgress);

    List<SoProgressModel> getAllSoSoProgress(UUID soId);
}
