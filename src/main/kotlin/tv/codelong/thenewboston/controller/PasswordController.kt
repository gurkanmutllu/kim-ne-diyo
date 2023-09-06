package tv.codelong.thenewboston.controller

import org.springframework.web.bind.annotation.*
import tv.codelong.thenewboston.dto.PasswordChangeDto
import tv.codelong.thenewboston.exception.ResultCode
import tv.codelong.thenewboston.exception.UserNotFoundException
import tv.codelong.thenewboston.service.user.UserService

class PasswordController {
    @RestController
    @RequestMapping("/api")
    class UserController(private val userService: UserService) {
        @PostMapping("/password-change")
        fun changePassword(@RequestBody payload: PasswordChangeDto): PasswordChangeDto {
            val user = userService.findByName(payload.name) ?: throw UserNotFoundException(ResultCode.NotFound)

            val currentPassword = payload.currentPassword
            val newPassword = payload.newPassword

            userService.updatePassword(payload.id, newPassword)
            return PasswordChangeDto(id = payload.id, name = payload.name, currentPassword = currentPassword, newPassword = newPassword)
        }
    }
}
