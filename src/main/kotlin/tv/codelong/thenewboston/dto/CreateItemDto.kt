package tv.codelong.thenewboston.dto

data class CreateItemDto(
    val name: String,
    val count: Int,
    val note: String?
)