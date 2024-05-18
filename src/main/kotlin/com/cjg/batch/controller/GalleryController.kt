package com.cjg.batch.controller

import com.cjg.batch.dto.GalleryDto
import com.cjg.batch.service.GalleryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GalleryController(
    private val galleryService: GalleryService
){

    @GetMapping("/list")
    fun list() : List<GalleryDto>{
        return galleryService.list()
    }
}