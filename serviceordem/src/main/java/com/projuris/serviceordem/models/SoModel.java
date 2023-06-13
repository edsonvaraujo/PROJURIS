package com.projuris.serviceordem.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_SO")
public class SoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID soId;
    private Long soNumber;
    @Column(nullable = false, length = 255)
    private String soComments;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Column(nullable = false)
    private LocalDateTime creationDate;
    private UUID technician;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private EquipmentModel soEquipments;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ClientModel soClients;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "sos", fetch = FetchType.LAZY )
    @Fetch(FetchMode.SUBSELECT)
    private Set<SoProgressModel> soProgress;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "service", fetch = FetchType.LAZY)
    private Set<ServiceOrderUserModel> serviceOrdemUserModels;
}
