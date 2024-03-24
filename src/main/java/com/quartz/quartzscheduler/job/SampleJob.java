package com.quartz.quartzscheduler.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@Slf4j
public class SampleJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String data = (String) jobExecutionContext.getMergedJobDataMap().get("data");
        log.info("Job is running");
        log.info("Job data : {}", data);
        //Implement your job logic here
    }
}
