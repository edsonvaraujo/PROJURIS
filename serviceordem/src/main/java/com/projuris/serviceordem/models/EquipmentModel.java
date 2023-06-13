package com.projuris.serviceordem.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "TB_EQUIPMENT")
public class EquipmentModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID equipmentId;
    @Column(nullable = false, length = 50)
    private String equipmentBrand;
    @Column(nullable = false, length = 50)
    private String equipmentModel;
    @Column(nullable = false, length = 20)
    private String equipmentSerial;
    @Column(nullable = false, length = 200)
    private String typeModels;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "soEquipments", fetch = FetchType.LAZY )
    @Fetch(FetchMode.SUBSELECT)
    private Set<SoModel> so;
}
