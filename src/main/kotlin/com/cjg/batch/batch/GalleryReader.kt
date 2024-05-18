package com.cjg.batch.batch

import com.cjg.batch.dto.GalleryDto
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.support.ListItemReader

class GalleryReader(list: MutableList<GalleryDto>) : ListItemReader<GalleryDto>(list){}