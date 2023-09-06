package tv.codelong.thenewboston.service.news.dtos

import org.springframework.web.multipart.MultipartFile
import tv.codelong.thenewboston.model.User
import tv.codelong.thenewboston.service.user.dtos.ShortUserDTO

data class UpdateNewsDto (
    val id: Long = 0,
    val header: String,
    val content: String,
    val imgFile: MultipartFile? = null,
)
