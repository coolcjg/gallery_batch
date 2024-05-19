package com.cjg.batch.entity

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime


@Entity(name = "gallery")
class Gallery {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val galleryId: Long? = null

    @Column(nullable = false)
    val mediaId: Long? = null

    @Column(nullable = false, length = 5)
    val type: String? = null

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    @Column(nullable = false)
    val regDate: LocalDateTime? = null

    @Column(nullable = false, length = 1)
    @ColumnDefault("'N'")
    var status: String? = null

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    @Column(nullable = true)
    val completeDate: LocalDateTime? = null

    @Column(nullable = false, length = 40)
    val thumbnailFilePath: String? = null

    @Column(nullable = false, length = 20)
    val thumbnailFileName: String? = null

    @Column(nullable = false, length = 40)
    val encodingFilePath: String? = null

    @Column(nullable = false, length = 20)
    val encodingFileName: String? = null
}