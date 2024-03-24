package com.quartz.quartzscheduler.controller;

import com.quartz.quartzscheduler.service.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/schedule")
public class ScheduleController {

    @Autowired
    private JobScheduler jobScheduler;

    @PostMapping("/job")
    public ResponseEntity<?> scheduleJob(@RequestBody String requestData) {
        boolean status = jobScheduler.scheduleJob(requestData);
        if(status){
            return ResponseEntity.ok("Job Scheduled Successfully");
        } else {
            return ResponseEntity.internalServerError().body("Failed to schedule a job");
        }
    }
}
