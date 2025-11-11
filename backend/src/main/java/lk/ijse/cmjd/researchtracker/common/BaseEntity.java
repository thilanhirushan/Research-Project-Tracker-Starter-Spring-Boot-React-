package lk.ijse.cmjd.researchtracker.common;

import javax.persistence.*;
import java.time.*;

@MappedSuperclass
public abstract class BaseEntity {
    @Column(nullable = false)
    protected LocalDateTime createdAt;
    @Column(nullable = false)
    protected LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = updatedAt = LocalDateTime.now();
    }
    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
