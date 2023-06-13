package com.projuris.serviceordem.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_ADDRESS")
public class AddressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID addressId;
    @Column(nullable = false, length = 100)
    private String addressState;
    @Column(nullable = false, length = 100)
    private String  addressCity;
    @Column(nullable = false, length = 100)
    private String addressNeighborhood;
    @Column(nullable = false, length = 255)
    private String addressStreet;
    @Column(nullable = false, length = 20)
    private String addressNumber;
    @Column(nullable = false, length = 15)
    private String AddressZipCode;




}
