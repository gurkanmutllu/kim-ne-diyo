package tv.codelong.thenewboston.service.user.dtos

import tv.codelong.thenewboston.model.Role
import tv.codelong.thenewboston.model.User

class ShortUserDTO (
    val id: Long,
    val name: String,
    val userName: String,
    val userSurname: String
) {
    companion object {
        fun create(user: User) = ShortUserDTO (
            id = user.id,
            name = user.name,
            userName = user.name,
            userSurname = user.userSurname
        )
    }
}