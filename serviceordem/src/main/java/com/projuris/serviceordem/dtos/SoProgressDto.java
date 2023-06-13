package com.projuris.serviceordem.dtos;

import com.projuris.serviceordem.enums.SoStatus;
import com.projuris.serviceordem.models.SoModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SoProgressDto {

    private UUID osProgressId;
    @NotBlank
    private String soprogress;
    @NotNull
    private LocalDateTime lastUpdateDate;
    @NotNull
    private SoStatus soStatus;
    @NotNull
    private UUID uuid;
    @NotNull
    private UUID sos;
}
