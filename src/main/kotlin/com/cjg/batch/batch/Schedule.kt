package com.cjg.batch.batch

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class Schedule {

    @Scheduled(fixedDelay = 1000)
    fun scheduleTaskUsingCronExpression(){

    }
}