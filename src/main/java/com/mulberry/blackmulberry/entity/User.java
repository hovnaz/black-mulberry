package com.mulberry.blackmulberry.entity;

import com.mulberry.blackmulberry.entity.model.UserRole;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_model")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @Column(nullable = false, unique = true)
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private String password;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at", nullable = false, updatable = false)
    private Date createAt;
}
