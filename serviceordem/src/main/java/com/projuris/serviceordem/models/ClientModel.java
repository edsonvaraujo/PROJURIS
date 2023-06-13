package com.projuris.serviceordem.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.mapping.ToOne;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_CLIENT")
public class ClientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID clientId;
    @Column(nullable = false, length = 255)
    private String clientName;
    @Column(nullable = false, length = 15, unique = true)
    private String clientTelephone;
    @Column(nullable = false, length = 150, unique = true)
    private String clientEmail;
    @OneToOne
    private AddressModel address;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "soClients", fetch = FetchType.LAZY )
    @Fetch(FetchMode.SUBSELECT)
    private Set<SoModel> so;
}
