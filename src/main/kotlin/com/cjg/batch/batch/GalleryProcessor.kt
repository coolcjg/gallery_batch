package com.cjg.batch.batch

import com.cjg.batch.entity.Gallery
import com.cjg.batch.service.GalleryService
import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component

@Component
class GalleryProcessor(
    private val galleryService : GalleryService
) : ItemProcessor<Gallery, com.cjg.batch.document.GalleryDoc> {

    override fun process(item: Gallery): com.cjg.batch.document.GalleryDoc? {
        return galleryService.convertEntityToDocument(item)
    }
}