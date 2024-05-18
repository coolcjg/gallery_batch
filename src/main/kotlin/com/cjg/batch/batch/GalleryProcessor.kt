package com.cjg.batch.batch

import com.cjg.batch.dto.GalleryDto
import org.springframework.batch.item.ItemProcessor


class GalleryProcessor : ItemProcessor<GalleryDto, String> {

    override fun process(item: GalleryDto): String? {
        return item.galleryId.toString()
    }

}