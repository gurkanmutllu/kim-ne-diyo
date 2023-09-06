package tv.codelong.thenewboston.service.user.dtos

import tv.codelong.thenewboston.model.Role
import tv.codelong.thenewboston.model.User

data class UserDto(
    val id: Long,
    val name: String,
    val userName: String,
    val userSurname: String,
    val userMail: String,
    val userPhone: String,
    val role: Role,
) {

    companion object {

        fun create(user: User) = UserDto (
            id = user.id,
            name = user.name,
            userName = user.name,
            userSurname = user.userSurname,
            userMail = user.userMail,
            userPhone = user.userPhone,
            role = user.role
            )
    }
}