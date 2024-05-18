package com.cjg.batch.batch

import org.apache.coyote.http11.Constants.a
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter

class GalleryWriter : ItemWriter<String> {

    override fun write(chunk: Chunk<out String>) {
        chunk.forEach{
            println(it)
        }
    }
}