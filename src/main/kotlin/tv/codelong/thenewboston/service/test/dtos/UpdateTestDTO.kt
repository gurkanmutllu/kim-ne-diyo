package tv.codelong.thenewboston.service.test.dtos

import tv.codelong.thenewboston.model.Test
import tv.codelong.thenewboston.service.user.dtos.ShortUserDTO

data class UpdateTestDTO(
    val id: Long,
    val testName: String?,
    val content: String?,
    val mediaFile: ByteArray? = null,
)
