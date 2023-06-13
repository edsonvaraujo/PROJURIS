package com.projuris.serviceordem.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class EquipmentDto {

    private UUID equipmentId;
    @NotBlank
    private String equipmentBrand;
    @NotBlank
    private String equipmentModel;
    @NotBlank
    private String equipmentSerial;
    @NotNull
    private String typeModels;
}
