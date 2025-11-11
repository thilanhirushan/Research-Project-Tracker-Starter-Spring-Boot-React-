package lk.ijse.cmjd.researchtracker.project;

import javax.persistence.*;
import javax.validation.constraints.*;
import lk.ijse.cmjd.researchtracker.common.BaseEntity;
import lk.ijse.cmjd.researchtracker.user.User;
import lombok.*;
import java.time.*;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Project extends BaseEntity {
    @Id
    private String id;

    @NotBlank
    private String title;

    @Column(length = 1000)
    private String summary;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PLANNING;

    @ManyToOne(optional = false)
    private User pi;

    private String tags;
    private LocalDate startDate;
    private LocalDate endDate;
}
