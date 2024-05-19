package com.cjg.batch.batch

import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class Schedule(
    val jobLauncher: JobLauncher,
    val job : Job
){
    @Scheduled(cron = "0/10 * * * * *")
    fun scheduleTaskUsingCronExpression(){

        val jobParameter = JobParametersBuilder().toJobParameters();
        jobLauncher.run(job, jobParameter)

    }
}