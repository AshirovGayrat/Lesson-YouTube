package com.company.entity;

import com.company.enums.AttachType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "attach")
public class AttachEntity {
    @Id
    private String id; // uuid

    @Column
    private String path;
    @Column
    private String duration;
    @Column
    private String extension;
    @Column(name = "origen_name")
    private String origenName;
    @Column()
    private Long size;
    @Enumerated(EnumType.STRING)
    private AttachType type;

    @Column
    private LocalDateTime createdDate = LocalDateTime.now();
}
