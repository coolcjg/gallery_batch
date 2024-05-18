package com.cjg.batch.repository

import com.cjg.batch.entity.Gallery
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GalleryRepository : JpaRepository<Gallery, Long> {
}