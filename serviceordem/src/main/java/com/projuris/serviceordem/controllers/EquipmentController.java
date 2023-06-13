package com.projuris.serviceordem.controllers;

import com.projuris.serviceordem.dtos.EquipmentDto;

import com.projuris.serviceordem.models.EquipmentModel;
import com.projuris.serviceordem.services.EquipmentService;
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

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/equip")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EquipmentController {

    @Autowired
    EquipmentService equipmentService;

    @PostMapping
    public ResponseEntity<Object> saveEquip(@RequestBody @Validated EquipmentDto equipmentDto){
        var equipmentModel = new EquipmentModel();
        BeanUtils.copyProperties(equipmentDto, equipmentModel);
        equipmentService.save(equipmentModel);
        log.info("User updated successfully equipmentId {} ", equipmentModel.getEquipmentId());
        return ResponseEntity.status(HttpStatus.CREATED).body(equipmentModel);
    }
    @GetMapping("/{equipmentId}")
    public ResponseEntity<Object> getOneEquipType(@PathVariable(value = "equipmentId") UUID equipmentId){

        Optional<EquipmentModel> equipmentOptional = equipmentService.findById(equipmentId);
        if (!equipmentOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipment not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(equipmentOptional.get());
    }
    @GetMapping
    public ResponseEntity<Page<EquipmentModel>> getAllEquip(SpecificationTemplate.EquipmentSpec spec,
                                                            @PageableDefault(page = 0, size = 10, sort = "equipmentId", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(equipmentService.findAll(spec, pageable));
    }
    @PutMapping("/{equipmentId}")
    public ResponseEntity<Object> updateEquipment(@PathVariable(value = "equipmentId")UUID equipmentId,
                                             @RequestBody @Valid EquipmentDto equipmentDto) {
        Optional<EquipmentModel> equipModelOptional = equipmentService.findById(equipmentId);
        if (!equipModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipment not Found");
        } else {
            var equipmentModel = equipModelOptional.get();
            BeanUtils.copyProperties(equipmentDto, equipmentModel);
            equipmentService.save(equipmentModel);
            log.info("User updated successfully equipmentId {} ", equipmentModel.getEquipmentId());
            return ResponseEntity.status(HttpStatus.OK).body(equipmentModel);
        }
    }
}
