package com.projuris.serviceordem.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.projuris.serviceordem.enums.SoStatus;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_SOPROGRESS")
public class SoProgressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID osProgressId;
    @Column(nullable = false, length = 255)
    private String soprogress;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SoStatus soStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Column(nullable = false)
    private LocalDateTime lastUpdateDate;
    @Column(nullable = false, length = 255)
    private UUID uuid;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private SoModel sos;
}
