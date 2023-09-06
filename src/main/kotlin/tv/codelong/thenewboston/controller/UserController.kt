package tv.codelong.thenewboston.controller

import org.springframework.web.bind.annotation.*
import tv.codelong.thenewboston.dto.*
import tv.codelong.thenewboston.exception.ResultCode
import tv.codelong.thenewboston.factory.ResponseFactory
import tv.codelong.thenewboston.service.user.UserService
import tv.codelong.thenewboston.service.user.dtos.UpdateUserDto
import tv.codelong.thenewboston.service.user.dtos.UserDto

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService,
    private val responseFactory: ResponseFactory
    ) {

    @GetMapping("/get-by-id/{id}")
    fun getUserById(@PathVariable id: Long): ResponseDto<UserDto> {
        return userService.findById(id)
    }

    @PutMapping("/update/{id}")
    fun updateUser(@RequestBody updateDto: UpdateUserDto): ResponseDto<Nothing> {
        userService.updateUser(updateDto)
        return responseFactory.success()
    }

    @DeleteMapping("/delete/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseDto<Nothing> {
        userService.deleteUser(id)
        return responseFactory.success()
    }

    @GetMapping("/get-all")
    fun getAllUsers(): ResponseDto<MutableList<UserDto>> {
        return ResponseDto(
            resultCode = ResultCode.Success,
            message = "List of all users: ",
            data = userService.getAllUsers()
        )
    }
}
