package com.cjg.batch.batch

import com.cjg.batch.document.GalleryDoc
import com.cjg.batch.service.GalleryService
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter
import org.springframework.stereotype.Component

@Component
class GalleryWriter(
    private val galleryService: GalleryService
) : ItemWriter<GalleryDoc> {

    override fun write(chunk: Chunk<out GalleryDoc>) {
        chunk.forEach{

            if(galleryService.existsByGalleryId(it.galleryId)){

                galleryService.updateGalleryComplete(it.galleryId)

            }else{
                val galleryDoc:GalleryDoc = galleryService.insertMongoDB(it)

                if(galleryDoc.galleryId == it.galleryId){
                    galleryService.updateGalleryComplete(it.galleryId)
                }
            }

        }
    }
}