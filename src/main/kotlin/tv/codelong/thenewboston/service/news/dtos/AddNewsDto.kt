package tv.codelong.thenewboston.service.news.dtos

import org.springframework.web.multipart.MultipartFile
import tv.codelong.thenewboston.model.News
import tv.codelong.thenewboston.service.Base64Service
import tv.codelong.thenewboston.service.user.dtos.ShortUserDTO

data class AddNewsDto(
    val header: String,
    val content: String,
    val imgFile: MultipartFile ?= null,
    val user: Long
)
