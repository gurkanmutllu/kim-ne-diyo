package tv.codelong.thenewboston.dto

import tv.codelong.thenewboston.model.Role

data class LoginResponseDto (
    val token: String,
    val role: Role
)