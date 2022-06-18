package com.company.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "tag")
public class TagEntity extends BaseEntity{

    @Column(name = "name_", nullable = false, unique = true)
    private String name;

}
