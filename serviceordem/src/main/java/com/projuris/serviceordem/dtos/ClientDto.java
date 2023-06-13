package com.projuris.serviceordem.dtos;

import com.projuris.serviceordem.models.AddressModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class ClientDto {

    private UUID clientId;
    @NotBlank
    private String clientName;
    @NotBlank
    private String clientTelephone;
    @NotBlank
    private String clientEmail;
    @NotBlank
    private String addressState;
    @NotBlank
    private String  addressCity;
    @NotBlank
    private String addressNeighborhood;
    @NotBlank
    private String addressStreet;
    @NotBlank
    private String addressNumber;
    @NotBlank
    private String addressZipCode;
}
