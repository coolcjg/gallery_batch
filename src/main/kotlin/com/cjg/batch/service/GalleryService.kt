package com.cjg.batch.service

import com.cjg.batch.dto.GalleryDto
import com.cjg.batch.repository.GalleryRepository
import org.springframework.stereotype.Service

@Service
class GalleryService(
    val galleryRepository : GalleryRepository,
){


    fun list(): MutableList<GalleryDto>{
        val galleryList =  galleryRepository.findByStatus("N")
        val galleryDtoList = mutableListOf<GalleryDto>()

        for(gallery in galleryList){
            val galleryDto = GalleryDto(gallery.galleryId)
            galleryDto.mediaId = gallery.mediaId
            galleryDto.type = gallery.type
            galleryDto.regDate = gallery.regDate
            galleryDto.thumbnailFilePath = gallery.thumbnailFilePath
            galleryDto.thumbnailFileName = gallery.thumbnailFileName
            galleryDto.encodingFilePath = gallery.encodingFilePath
            galleryDto.encodingFileName = gallery.encodingFileName

            galleryDtoList.add(galleryDto)
        }
        return galleryDtoList
    }



}