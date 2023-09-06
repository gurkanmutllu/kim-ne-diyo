package tv.codelong.thenewboston.service.user.dtos

data class AddUserDto (
    val id: Long,
    val name: String,
    val userName: String,
    val userSurname: String,
    val userMail: String,
    val userPhone: String,
)
