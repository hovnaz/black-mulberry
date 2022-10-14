package com.mulberry.blackmulberry.entity;

import com.mulberry.blackmulberry.entity.model.Role;
import lombok.*;
import javax.persistence.*;
import java.sql.Date;

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
    private String email;
    private String phone;
    private Role role;
    private String password;
    private Date createAt;
    @ManyToOne
    private CategoryProduct catProduct;
    private Boolean isDelete;
    @ManyToOne
    private User user;
}
