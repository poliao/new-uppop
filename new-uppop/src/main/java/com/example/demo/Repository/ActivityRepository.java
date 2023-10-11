package com.example.demo.Repository;

import com.example.demo.Model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    // สามารถเพิ่มเมธอดเพิ่มเติมได้ตามความต้องการ
}