package com.mulberry.blackmulberry.entity;

import com.mulberry.blackmulberry.entity.model.Role;
import lombok.*;
import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_model")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    @Column(nullable = false, unique = true)
    private String email;
    private String phone;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    private String password;
    private Date createAt;
}
