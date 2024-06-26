package com.cjg.batch.repository

import com.cjg.batch.entity.Gallery
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GalleryRepository : JpaRepository<Gallery, Long> {
    fun findByStatus(status : String) : List<Gallery>
    fun findByGalleryId(galleryId : Long) : Gallery
}