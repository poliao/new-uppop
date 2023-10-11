package com.example.demo.Model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_user;

    private String username;
    private String faculty;
    private String email;
    private String typeuser;
    private String password;
    private String firstname;
    private String lastname;

    private LocalDateTime date_time_add;
    // Getters and setters
}
