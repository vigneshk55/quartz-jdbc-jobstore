package com.quartz.quartzscheduler.service;

import com.quartz.quartzscheduler.job.SampleJob;
import jdk.jfr.Category;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JobSchedulerImpl implements JobScheduler{

    @Autowired
    private Scheduler scheduler;


    @Override
    public boolean scheduleJob(String requestData) {
        JSONObject json = new JSONObject(requestData);
        JobDetail job = buildJob(json);
        Trigger trigger = buildTrigger(job);
        try {
            scheduler.scheduleJob(job, trigger);
            log.info("Scheduled job and trigger in quartz scheduler");
            return true;
        } catch (SchedulerException e) {
            log.error("Exception occurred : {}", e.getMessage());
            return false;
        }
    }

    private JobDetail buildJob(JSONObject request) {
        String jobName = (String) request.get("name");
        String group = (String) request.get("group");
        String description = (String) request.get("description");
        String data = (String) request.get("data");
        return JobBuilder.newJob(SampleJob.class)
                .withIdentity(jobName, group)
                .withDescription(description)
                .usingJobData("data", data)
                .storeDurably()
                .build();
    }

    private Trigger buildTrigger(JobDetail job) {
        return TriggerBuilder.newTrigger()
                .forJob(job)
                .withIdentity("trigger")
                .startNow() //You can modify this line by using startAt() for timely trigger
                .build();
    }
}
