package com.example.demo.controller;

import com.example.demo.Model.Activity;
import com.example.demo.Repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityController(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    // สร้าง Activity
    @PostMapping
    public ResponseEntity<Activity> createActivity(@RequestBody Activity activity) {
        Activity createdActivity = activityRepository.save(activity);
        return new ResponseEntity<>(createdActivity, HttpStatus.CREATED);
    }

    // ดึงข้อมูล Activity ทั้งหมด
    @GetMapping
    public ResponseEntity<List<Activity>> getAllActivities() {
        List<Activity> activities = activityRepository.findAll();
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    // ดึงข้อมูล Activity ตาม ID
    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable Long id) {
        return activityRepository.findById(id)
                .map(activity -> new ResponseEntity<>(activity, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // อัพเดท Activity ตาม ID
    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable Long id, @RequestBody Activity updatedActivity) {
        return activityRepository.findById(id)
                .map(existingActivity -> {
                    updatedActivity.setID_activity(existingActivity.getID_activity());
                    Activity savedActivity = activityRepository.save(updatedActivity);
                    return new ResponseEntity<>(savedActivity, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // ลบ Activity ตาม ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        activityRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

