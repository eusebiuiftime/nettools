package dev.eusebiu.nettools.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;

@Getter
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }
}
