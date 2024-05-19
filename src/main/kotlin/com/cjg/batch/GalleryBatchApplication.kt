package com.cjg.batch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
@EnableMongoAuditing
class GalleryBatchApplication

fun main(args: Array<String>) {
    runApplication<GalleryBatchApplication>(*args)
}
