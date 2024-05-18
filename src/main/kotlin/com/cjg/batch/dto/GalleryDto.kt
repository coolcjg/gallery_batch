package com.cjg.batch.dto

import java.time.LocalDateTime

data class GalleryDto(
    val galleryId: Long?
){
    var mediaId: Long? = null
    var type: String? = null
    var regDate: LocalDateTime? = null
    var status: String? = null
    var completeDate: LocalDateTime? = null
    var thumbnailFilePath: String? = null
    var thumbnailFileName: String? = null
    var encodingFilePath: String? = null
    var encodingFileName: String? = null
}