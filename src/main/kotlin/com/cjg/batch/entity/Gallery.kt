package com.cjg.batch.entity

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime


@Entity(name = "gallery")
class Gallery {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var galleryId: Long? = null

    @Column(nullable = false)
    var mediaId: Long? = null

    @Column(nullable = false, length = 5)
    var type: String? = null

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    @Column(nullable = false)
    var regDate: LocalDateTime? = null

    @Column(nullable = false, length = 1)
    @ColumnDefault("'N'")
    var status: String? = null

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    @Column(nullable = true)
    var completeDate: LocalDateTime? = null

    @Column(nullable = false, length = 40)
    var thumbnailFilePath: String? = null

    @Column(nullable = false, length = 20)
    var thumbnailFileName: String? = null

    @Column(nullable = false, length = 40)
    var encodingFilePath: String? = null

    @Column(nullable = false, length = 20)
    var encodingFileName: String? = null
}