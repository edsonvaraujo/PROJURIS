package com.projuris.serviceordem.controllers;

import com.projuris.serviceordem.models.SoProgressModel;
import com.projuris.serviceordem.services.SoProgressService;
import com.projuris.serviceordem.specifications.SpecificationTemplate;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/soprogress")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SoProgressController {

    @Autowired
    SoProgressService soProgressService;

    @GetMapping("/{soId}/soprogress")
    public ResponseEntity<List<SoProgressModel>> getAllSoSoProgress(@PathVariable(value = "soId") UUID soId,
                                                                    SpecificationTemplate.SoSpec spec,
                                                                    @PageableDefault(page = 0, size = 10, sort = "soNumber", direction = Sort.Direction.ASC) Pageable pageable){
        List<SoProgressModel> listos = soProgressService.getAllSoSoProgress(soId);
        log.info("{}", listos);

        return ResponseEntity.status(HttpStatus.CREATED).body(listos);
    }
}
