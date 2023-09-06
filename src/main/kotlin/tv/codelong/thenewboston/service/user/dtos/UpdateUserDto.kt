package tv.codelong.thenewboston.service.user.dtos

import tv.codelong.thenewboston.model.User

data class UpdateUserDto (
    val id: Long,
    val name: String?,
    val userName: String?,
    val userSurname: String?,
    val userMail: String?,
    val userPhone: String?
)