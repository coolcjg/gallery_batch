package com.cjg.batch.batch

import com.cjg.batch.dto.GalleryDto
import com.cjg.batch.service.GalleryService
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class JobConfiguration(
    val galleryService: GalleryService
    ) {

    @Bean
    fun job(jobRepository: JobRepository, transactionManager:PlatformTransactionManager) : Job {
        return JobBuilder("testJob19", jobRepository)
            .start(step(jobRepository, transactionManager))
            .build()
    }

    @Bean
    fun step(jobRepository: JobRepository, platformTransactionManager: PlatformTransactionManager,
             ): Step {

                val list = galleryService.list()

                return StepBuilder("collectStep", jobRepository)
                    .chunk<GalleryDto, String>(3, platformTransactionManager)
                    .reader(GalleryReader(list))
                    .processor(GalleryProcessor())
                    .writer(GalleryWriter())
                    .build()
    }
}