package com.projuris.serviceordem.dtos;


import com.projuris.serviceordem.enums.SoStatus;
import com.projuris.serviceordem.models.ClientModel;
import com.projuris.serviceordem.models.EquipmentModel;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SoDto {

    public interface SoView{
        public static interface SoPost{}
        public static interface SodPut{}
    }

    private UUID soId;
    private Long soNumber;
    @NotBlank(groups = {SoView.SoPost.class, SoView.SodPut.class})
    private String soComments;
    @NotNull(groups = {SoView.SoPost.class, SoView.SodPut.class})
    private UUID technician;
    @NotNull(groups = {SoView.SoPost.class, SoView.SodPut.class})
    private UUID soEquipments;
    @NotNull(groups = {SoView.SoPost.class, SoView.SodPut.class})
    private UUID soClients;
    private UUID osProgressId;
    @NotBlank(groups = SoView.SodPut.class)
    private String soprogress;
    private LocalDateTime lastUpdateDate;
    @NotNull(groups = SoView.SodPut.class)
    private SoStatus soStatus;
    @NotNull(groups = SoView.SodPut.class)
    private UUID uuid;
    private UUID sos;


}
