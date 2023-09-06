package tv.codelong.thenewboston.dto

data class UpdateItemDto (
    val id: Long,
    val name: String,
    val count: Int,
    val note: String?
)