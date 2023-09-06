package tv.codelong.thenewboston.service.test.dtos

import tv.codelong.thenewboston.model.Test
import tv.codelong.thenewboston.model.User
import tv.codelong.thenewboston.service.user.dtos.ShortUserDTO

data class AddTestDto (
    val testName: String,
    val content: String,
    val mediaFile: ByteArray? = null,
    val user: Long
)
