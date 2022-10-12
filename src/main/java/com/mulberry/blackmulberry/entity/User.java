package com.mulberry.blackmulberry.entity;

import com.mulberry.blackmulberry.entity.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    private String password;
    private Date createAt;
    private Boolean isDelete;
}