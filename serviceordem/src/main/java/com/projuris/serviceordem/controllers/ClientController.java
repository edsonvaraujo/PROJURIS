package com.projuris.serviceordem.controllers;


import com.projuris.serviceordem.dtos.ClientDto;
import com.projuris.serviceordem.dtos.EquipmentDto;
import com.projuris.serviceordem.models.AddressModel;
import com.projuris.serviceordem.models.ClientModel;
import com.projuris.serviceordem.models.EquipmentModel;
import com.projuris.serviceordem.services.AddressService;
import com.projuris.serviceordem.services.ClientService;
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
import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/clients")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ClientController {

    @Autowired
    ClientService clientService;

    @Autowired
    AddressService addressService;

    @Transactional
    @PostMapping
    public ResponseEntity<Object> saveClient(@RequestBody @Validated ClientDto clientDto){
        var clientModel = new ClientModel();

        if(clientService.existsByClientEmail(clientDto.getClientEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Client is Already Taken!");
        }
        BeanUtils.copyProperties(clientDto, clientModel);
        clientService.save(clientModel);
        var addressModel = new AddressModel();
        addressModel.setAddressState(clientDto.getAddressState());
        addressModel.setAddressCity(clientDto.getAddressCity());
        addressModel.setAddressNeighborhood(clientDto.getAddressNeighborhood());
        addressModel.setAddressStreet(clientDto.getAddressStreet());
        addressModel.setAddressNumber(clientDto.getAddressNumber());
        addressModel.setAddressZipCode(clientDto.getAddressZipCode());
        addressService.save(addressModel);
        clientModel.setAddress(addressModel);
        clientService.save(clientModel);
        log.info("Client updated successfully clientId {} ", clientModel.getClientId());
        return ResponseEntity.status(HttpStatus.CREATED).body(clientModel);
    }
    @GetMapping
    public ResponseEntity<Page<ClientModel>> getAllClient(SpecificationTemplate.ClientSpec spec,
                                                         @PageableDefault(page = 0, size = 10, sort = "clientId", direction = Sort.Direction.ASC) Pageable pageable){
        Page<ClientModel> clientModelPage = clientService.findAll(spec, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(clientModelPage);
    }
    @GetMapping("/{clientId}")
    public ResponseEntity<Object> getOneClient(@PathVariable(value = "clientId") UUID clientId){
        Optional<ClientModel> clientModelOptional = clientService.findById(clientId);
        if(!clientModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not Found");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(clientModelOptional.get());
        }
    }
    @PutMapping("/{clientId}")
    public ResponseEntity<Object> updateClient(@PathVariable(value = "clientId")UUID clientId,
                                             @RequestBody @Valid ClientDto clientDto) {
        Optional<ClientModel> clientModelOptional = clientService.findById(clientId);
        if (!clientModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not Found");
        } else {
            var clientModel = clientModelOptional.get();
            BeanUtils.copyProperties(clientDto, clientModel);
            var addressModel = clientModelOptional.get().getAddress();
            addressModel.setAddressState(clientDto.getAddressState());
            addressModel.setAddressCity(clientDto.getAddressCity());
            addressModel.setAddressNeighborhood(clientDto.getAddressNeighborhood());
            addressModel.setAddressStreet(clientDto.getAddressStreet());
            addressModel.setAddressNumber(clientDto.getAddressNumber());
            addressModel.setAddressZipCode(clientDto.getAddressZipCode());
            addressService.save(addressModel);
            clientModel.setAddress(addressModel);
            clientService.save(clientModel);
            log.info("Client updated successfully clientId {} ", clientModel.getClientId());
            return ResponseEntity.status(HttpStatus.OK).body(clientModel);
        }
    }

}
