package tv.codelong.thenewboston.service.test.dtos

import tv.codelong.thenewboston.model.Test

data class ShortTestDTO (
    val id: Long,
    val testName: String
) {
    companion object {
        fun create(test: Test) = ShortTestDTO(
            id = test.id,
            testName = test.testName
        )
    }
}
