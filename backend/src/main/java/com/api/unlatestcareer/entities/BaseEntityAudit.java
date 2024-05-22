package com.api.unlatestcareer.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseEntityAudit extends BaseEntity implements Serializable {
    @CreationTimestamp
    @Column(name="created_at",updatable=false)
    private LocalDate createdAt;

    @UpdateTimestamp
    @Column(name="updated_at")
    private LocalDate updatedAt;

    @Column
    private boolean enabled;
}
