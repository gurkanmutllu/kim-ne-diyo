package tv.codelong.thenewboston.controller


import org.springframework.web.bind.annotation.*
import tv.codelong.thenewboston.dto.*
import tv.codelong.thenewboston.service.AuthService

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = ["http://localhost:3000"])
class AuthController(
        private val authService: AuthService,
) {
    @PostMapping("/register")
    fun register(@RequestBody payload: RegisterDto): ResponseDto<RegisterDto> {
        return authService.addUser(payload)
    }

    @PostMapping("/login")
    fun login(@RequestBody payload: LoginDto): ResponseDto<LoginResponseDto> {
        return authService.loginUser(payload)
    }
}
