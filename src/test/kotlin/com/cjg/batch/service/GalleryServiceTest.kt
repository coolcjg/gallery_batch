package com.cjg.batch.service

import com.cjg.batch.document.GalleryDoc
import com.cjg.batch.entity.Gallery
import com.cjg.batch.repository.GalleryMongoRepository
import com.cjg.batch.repository.GalleryRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.mockk
import java.time.LocalDateTime

class GalleryServiceTest : BehaviorSpec(  {

    val galleryMongoRepository  =  mockk<GalleryMongoRepository>(relaxed = true)
    val galleryRepository =  mockk<GalleryRepository>(relaxed = true)

    @InjectMockKs
    val galleryService = GalleryService(galleryMongoRepository, galleryRepository)

    Given("list"){

        val galleryList = mutableListOf<Gallery>();
        val gallery = Gallery();
        gallery.galleryId = 1L;
        gallery.mediaId =1L;
        gallery.type = "image"
        gallery.regDate = LocalDateTime.now()
        gallery.thumbnailFilePath = "/encoding/2024/05/19/"
        gallery.thumbnailFileName = "103.jpg"
        gallery.encodingFilePath = "/encoding/2024/05/19/"
        gallery.encodingFileName = "103.jpg"

        galleryList.add(gallery)

        every{galleryRepository.findByStatus("N")} answers{galleryList}

        When("함수 실행"){
            val result = galleryService.list();

            Then("결과 확인"){
                result.get(0).galleryId shouldBe 1L;
            }
        }
    }

    Given("convertEntityToDocument"){

        val gallery = Gallery();
        gallery.galleryId = 1L;
        gallery.mediaId =1L;
        gallery.type = "image"
        gallery.regDate = LocalDateTime.now()
        gallery.thumbnailFilePath = "/encoding/2024/05/19/"
        gallery.thumbnailFileName = "103.jpg"
        gallery.encodingFilePath = "/encoding/2024/05/19/"
        gallery.encodingFileName = "103.jpg"

        When("함수 실행"){

            val result = galleryService.convertEntityToDocument(gallery);

            Then("결과 확인"){
                result.galleryId = gallery.galleryId
            }
        }
    }

    Given("existsByGalleryId"){

        val galleryId = 1L;

        When("함수 실행"){

            every{galleryMongoRepository.existsByGalleryId(galleryId)} answers{true}
            val result = galleryService.existsByGalleryId(galleryId);

            Then("결과 확인"){
                result shouldBe true;
            }
        }
    }

    Given("insertMongoDB"){

        val galleryDoc = GalleryDoc();
        galleryDoc.galleryId = 1L;

        When("함수 실행"){

            every{galleryMongoRepository.save(galleryDoc)} answers{galleryDoc}
            val result = galleryService.insertMongoDB(galleryDoc);

            Then("결과 확인"){
                result.galleryId shouldBe galleryDoc.galleryId;
            }
        }
    }

    Given("updateGalleryComplete"){

        val galleryId = 1L;
        val gallery = Gallery();
        gallery.galleryId = galleryId;

        When("함수 실행"){

            every{galleryRepository.findByGalleryId(galleryId)} answers{gallery}
            galleryService.updateGalleryComplete(galleryId);

            Then("결과 확인"){
                gallery.status shouldBe "Y";
                gallery.completeDate.shouldNotBeNull();

            }
        }
    }
})