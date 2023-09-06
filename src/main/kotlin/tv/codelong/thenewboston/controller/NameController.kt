package tv.codelong.thenewboston.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tv.codelong.thenewboston.dto.NameDto
import tv.codelong.thenewboston.exception.ApiException
import tv.codelong.thenewboston.exception.ResultCode
import tv.codelong.thenewboston.service.user.UserService

@RestController
@RequestMapping("/api")
class NameController(
    private val userService: UserService,
) {
    @GetMapping("/name/{name}")
    fun name(@PathVariable name: String): NameDto {
        val user = userService.findByName(name) ?: throw ApiException(ResultCode.ValidationError, "User not found")
        return NameDto(
                name = user.name,
        )
    }
}
