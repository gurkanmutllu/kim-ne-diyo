package tv.codelong.thenewboston.service.test.dtos

import tv.codelong.thenewboston.model.Test
import tv.codelong.thenewboston.service.user.dtos.ShortUserDTO

data class TestDto (
    val id: Long,
    val testName: String,
    val content: String,
    val mediaFile: ByteArray? = null,
    val user: Long
) {
    companion object {
        fun create(test: Test) = TestDto(
            id = test.id,
            testName = test.testName,
            content = test.content,
            mediaFile = test.mediaFile,
            user = test.user.id
        )
    }
}
