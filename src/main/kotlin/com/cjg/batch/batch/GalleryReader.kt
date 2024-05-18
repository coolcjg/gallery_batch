package com.cjg.batch.batch

import org.springframework.batch.item.ItemReader

class GalleryReader(
    var count : Int = 0
): ItemReader<Int>
{

    override fun read(): Int? {

        count++

        if(count == 3)
            return null

        return 3
    }
}