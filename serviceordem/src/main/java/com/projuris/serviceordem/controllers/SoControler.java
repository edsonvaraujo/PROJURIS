package com.projuris.serviceordem.controllers;

import com.projuris.serviceordem.dtos.SoDto;
import com.projuris.serviceordem.models.ClientModel;
import com.projuris.serviceordem.models.EquipmentModel;
import com.projuris.serviceordem.models.SoModel;
import com.projuris.serviceordem.models.SoProgressModel;
import com.projuris.serviceordem.services.ClientService;
import com.projuris.serviceordem.services.EquipmentService;
import com.projuris.serviceordem.services.SoProgressService;
import com.projuris.serviceordem.services.SoService;
import com.projuris.serviceordem.specifications.SpecificationTemplate;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/so")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SoControler {
    @Autowired
    SoService soService;

    @Autowired
    ClientService clientService;

    @Autowired
    EquipmentService equipmentService;

    @Autowired
    SoProgressService soProgressService;

    @PostMapping
    public ResponseEntity<Object> saveSo(@RequestBody @Validated(SoDto.SoView.SoPost.class) SoDto soDto) {
        if (!clientService.existsByClientId(soDto.getSoClients())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Client not fond!");
        }
        if (!equipmentService.existsByEquipmentId(soDto.getSoEquipments())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Equipment not found!");
        }
        var soModel = new SoModel();
        BeanUtils.copyProperties(soDto, soModel);
        soModel.setSoClients(clientService.findById(soDto.getSoClients()).get());
        soModel.setSoEquipments(equipmentService.findById(soDto.getSoEquipments()).get());
        soModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        soModel.setSoNumber(soService.getMaxSoNumber()+1);
        soService.save(soModel);
        log.info("So saved successfully userId {} ", soModel.getSoId());
        return ResponseEntity.status(HttpStatus.CREATED).body(soModel);
    }
    @GetMapping("/{soId}")
    public ResponseEntity<Object> getOneSo(@PathVariable(value = "soId") UUID soId){

        Optional<SoModel> soOptional = soService.findById(soId);
        if (!soOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("SO not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(soOptional.get());
    }
    @GetMapping
    public ResponseEntity<Page<SoModel>> getAllSo(SpecificationTemplate.SoSpec spec,
                                                            @PageableDefault(page = 0, size = 10, sort = "soNumber", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(soService.findAll(spec, pageable));
    }




    @Transactional
    @PutMapping("/{soId}")
    public ResponseEntity<Object> updateEquipment(@PathVariable(value = "soId")UUID soId,
                                                  @RequestBody @Validated(SoDto.SoView.SodPut.class) SoDto soDto) {
        Optional<SoModel> soModelOptional = soService.findById(soId);
        if (!soModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("So not Found");
        }
        Optional<ClientModel> clientModelOptional = clientService.findById(soDto.getSoClients());
        if (!clientModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not Found");
        }
        Optional<EquipmentModel> equipmentModelOptional = equipmentService.findById(soDto.getSoEquipments());
        if(!equipmentModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipment not Found");
        }
            var soModel = soModelOptional.get();
            var soProgress = new SoProgressModel();
            BeanUtils.copyProperties(soDto, soModel);
            BeanUtils.copyProperties(soDto,soProgress);
            soProgress.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
            soModel.setSoClients(clientModelOptional.get());
            soModel.setSoEquipments(equipmentModelOptional.get());
            soProgress.setSos(soModel);
            soService.save(soModel);
            soProgressService.save(soProgress);
            log.info("SO updated successfully soId {} ", soModel.getSoId());
            return ResponseEntity.status(HttpStatus.OK).body(soModel);
    }
}
