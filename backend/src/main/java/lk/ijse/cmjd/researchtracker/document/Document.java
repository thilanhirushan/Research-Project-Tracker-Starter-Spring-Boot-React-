package lk.ijse.cmjd.researchtracker.document;

import javax.persistence.*;
import lk.ijse.cmjd.researchtracker.common.BaseEntity;
import lk.ijse.cmjd.researchtracker.project.Project;
import lk.ijse.cmjd.researchtracker.user.User;
import lombok.*;
import java.time.*;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Document extends BaseEntity {
    @Id
    private String id;
    @ManyToOne(optional = false)
    private Project project;
    private String title;
    @Column(length = 1000)
    private String description;
    private String urlOrPath;
    @ManyToOne(optional = false)
    private User uploadedBy;
    private LocalDateTime uploadedAt;

    @PrePersist
    public void onUpload() {
        if (uploadedAt == null) uploadedAt = LocalDateTime.now();
    }
}
