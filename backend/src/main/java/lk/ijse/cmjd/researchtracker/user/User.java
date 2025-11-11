package lk.ijse.cmjd.researchtracker.user;

import javax.persistence.*;
import javax.validation.constraints.*;
import lk.ijse.cmjd.researchtracker.common.BaseEntity;
import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    private String id;

    @Column(unique = true, nullable = false)
    @Email
    private String username; // email

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;
}
