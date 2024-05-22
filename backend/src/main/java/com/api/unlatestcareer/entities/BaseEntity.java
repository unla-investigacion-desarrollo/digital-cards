package com.api.unlatestcareer.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;
}
