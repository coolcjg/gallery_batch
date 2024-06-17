package com.cjg.batch.controller

import com.cjg.batch.dto.GalleryDto
import com.cjg.batch.service.GalleryService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.mongodb.core.mapping.MongoMappingContext
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [GalleryController::class])
@AutoConfigureMockMvc
class GalleryControllerTest {

    @Autowired
    private lateinit var mockMvc : MockMvc;

    @Autowired
    private lateinit var objectMapper: ObjectMapper;

    @MockBean(name = "mongoMappingContext")
    private lateinit var mongoMappingContext : MongoMappingContext;

    @MockBean
    private lateinit var galleryService: GalleryService;

    @Test
    @DisplayName("list")
    fun listTest(){

        val galleryDtoList = mutableListOf<GalleryDto>()
        val galleryDto = GalleryDto(1L);
        galleryDtoList.add(galleryDto);

        BDDMockito.given(galleryService.list()).willReturn(galleryDtoList);

        val result = objectMapper.writeValueAsString(galleryDtoList);

        mockMvc.perform(get("/list"))
            .andExpect(status().isOk)
            .andExpect(content().string(result))
            .andDo(MockMvcResultHandlers.print());
    }
}