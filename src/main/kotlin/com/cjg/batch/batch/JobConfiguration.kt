package com.cjg.batch.batch

import com.cjg.batch.dto.GalleryDto
import com.cjg.batch.service.GalleryService
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.support.ListItemReader
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class JobConfiguration(
    val galleryService: GalleryService
    ) {

    @Bean
    fun job(jobRepository: JobRepository, transactionManager:PlatformTransactionManager) : Job {
        println("Job 빈 생성")
    return JobBuilder("BatchJob", jobRepository)
        .start(step(jobRepository, transactionManager))
        .incrementer(RunIdIncrementer())
        .build()
    }

    @Bean
    @JobScope
    fun step(jobRepository: JobRepository, platformTransactionManager: PlatformTransactionManager,
             ): Step {

                return StepBuilder("Step", jobRepository)
                    .chunk<GalleryDto, String>(2, platformTransactionManager)
                    .reader(reader())
                    .processor(GalleryProcessor())
                    .writer(GalleryWriter())
                    .allowStartIfComplete(true)
                    .build()
    }

    @Bean
    @StepScope
    fun reader() : ListItemReader<GalleryDto> {
        return ListItemReader<GalleryDto>(galleryService.list())
    }
}