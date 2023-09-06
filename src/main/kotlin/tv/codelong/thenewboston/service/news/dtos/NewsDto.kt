package tv.codelong.thenewboston.service.news.dtos

import tv.codelong.thenewboston.model.News
import tv.codelong.thenewboston.service.Base64Service
import tv.codelong.thenewboston.service.user.dtos.ShortUserDTO

data class NewsDto (
    val id: Long,
    val header: String?,
    val content: String?,
    val imgFile: String? = null,
    val user: Long
) {
    companion object {
        fun create(news: News) = NewsDto(
            id = news.id,
            header = news.header,
            content = news.content,
            imgFile = Base64Service.byteArrayToBase64(news.imgFile),
            user = news.user.id
        )
    }
}
