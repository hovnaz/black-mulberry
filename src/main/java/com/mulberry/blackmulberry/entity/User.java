package com.mulberry.blackmulberry.entity;

import com.mulberry.blackmulberry.entity.model.UserRole;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    private int id;
    private String name;
    private String surname;
    @Column(nullable = false, unique = true)
    private String email;
    private String phone;
    @Enumerated(value = EnumType.STRING)
    private UserRole role;
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createAt;
}
