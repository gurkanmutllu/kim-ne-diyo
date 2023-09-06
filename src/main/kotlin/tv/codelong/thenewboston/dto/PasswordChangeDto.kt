package tv.codelong.thenewboston.dto

data class PasswordChangeDto (
    val id: Long,
    val name: String,
    val currentPassword: String,
    val newPassword: String
)

