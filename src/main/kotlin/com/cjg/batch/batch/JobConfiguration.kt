package com.cjg.batch.batch

import com.cjg.batch.entity.Gallery
import jakarta.persistence.EntityManagerFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.database.JpaPagingItemReader
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class JobConfiguration(
    val  entityManagerFactory : EntityManagerFactory,
    val  galleryProcessor: GalleryProcessor,
    val  galleryWriter: GalleryWriter
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
                    .chunk<Gallery, com.cjg.batch.document.GalleryDoc>(1, platformTransactionManager)
                    .reader(galleryPagingItemReader())
                    .processor(galleryProcessor)
                    .writer(galleryWriter)
                    .allowStartIfComplete(true)
                    .build()
    }

    @Bean
    @StepScope
    fun galleryPagingItemReader() : JpaPagingItemReader<Gallery>{
        val jpaPagingItemReader = JpaPagingItemReaderBuilder<Gallery>()
                                    .queryString("SELECT g FROM gallery g WHERE g.status = 'N'")
            .pageSize(1)
            .entityManagerFactory(entityManagerFactory)
            .name("pagingReader")
            .build()

        return jpaPagingItemReader

    }
}