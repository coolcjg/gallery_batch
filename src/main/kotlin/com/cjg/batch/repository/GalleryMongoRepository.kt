package com.cjg.batch.repository

import com.cjg.batch.document.GalleryDoc
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface GalleryMongoRepository : MongoRepository<GalleryDoc, Long> {

    fun existsByGalleryId(galleryId : Long?) : Boolean

}