package com.example.demo.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_activity;

    private LocalDateTime date_time_ac;
    private String location;
    private String nameactivity;
    private String category;
    private String detail; // เพิ่มฟิลด์ detail
    @CreationTimestamp
    private LocalDateTime date_time_add;
}
