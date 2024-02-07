package com.myblog.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    //@Column(unique = true)
    private String username;
    //@Column(unique = true)
    private String password;
    private String email;
}
