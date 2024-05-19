package com.cjg.batch.service

import com.cjg.batch.document.GalleryDoc
import com.cjg.batch.dto.GalleryDto
import com.cjg.batch.entity.Gallery
import com.cjg.batch.repository.GalleryMongoRepository
import com.cjg.batch.repository.GalleryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class GalleryService(
    private val galleryMongoRepository: GalleryMongoRepository
){

    @Autowired
    lateinit var galleryRepository : GalleryRepository


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

    fun convertEntityToDocument(gallery:Gallery): GalleryDoc {
        val galleryDoc = GalleryDoc()

        galleryDoc.galleryId = gallery.galleryId
        galleryDoc.mediaId = gallery.mediaId
        galleryDoc.regDate = gallery.regDate
        galleryDoc.type = gallery.type
        galleryDoc.encodingFilePath = gallery.encodingFilePath
        galleryDoc.encodingFileName = gallery.encodingFileName
        galleryDoc.thumbnailFilePath =gallery.thumbnailFilePath
        galleryDoc.thumbnailFileName = gallery.thumbnailFileName

        return galleryDoc
    }

    fun existsByGalleryId(galleryId:Long?) : Boolean{
        return galleryMongoRepository.existsByGalleryId(galleryId)
    }
    fun insertMongoDB(galleryDoc: GalleryDoc): GalleryDoc {
        return galleryMongoRepository.save(galleryDoc)
    }
    fun updateGalleryComplete(galleryId : Long?){
        if(galleryId != null){
            val gallery:Gallery  = galleryRepository.findByGalleryId(galleryId)
            gallery.status = "Y"
            gallery.completeDate = LocalDateTime.now()
        }
    }

}