package tv.codelong.thenewboston.dto

data class ItemDto (
    val id: Long,
    val name: String,
    val count: Int,
    val note: String?
)