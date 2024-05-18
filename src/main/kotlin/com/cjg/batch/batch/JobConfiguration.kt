package com.cjg.batch.batch

import com.cjg.batch.dto.GalleryDto
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class JobConfiguration(
    ) {

    @Bean
    fun job(jobRepository: JobRepository, transactionManager:PlatformTransactionManager) : Job {
        return JobBuilder("testJob15", jobRepository)
            .start(step(jobRepository, transactionManager))
            .build()
    }

    @Bean
    fun step(jobRepository: JobRepository, platformTransactionManager: PlatformTransactionManager,
             ): Step {
                return StepBuilder("collectStep", jobRepository)
                    .chunk<Int, String>(3, platformTransactionManager)
                    .reader(GalleryReader())
                    .processor(GalleryProcessor())
                    .writer(GalleryWriter())

//                    .tasklet({ _, _ ->
//                        println("hello world")
//                        RepeatStatus.FINISHED
//                    }, platformTransactionManager)

                .build()
    }

}