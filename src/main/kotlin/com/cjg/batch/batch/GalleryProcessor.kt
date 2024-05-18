package com.cjg.batch.batch

import org.springframework.batch.item.ItemProcessor


class GalleryProcessor : ItemProcessor<Int, String> {

    override fun process(item: Int): String? {
        println(113333)
        return item.toString()
    }

}