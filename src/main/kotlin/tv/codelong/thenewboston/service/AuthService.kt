package tv.codelong.thenewboston.service

import org.springframework.stereotype.Service
import tv.codelong.thenewboston.dto.*
import tv.codelong.thenewboston.exception.ApiException
import tv.codelong.thenewboston.exception.ResultCode
import tv.codelong.thenewboston.exception.UserNotFoundException
import tv.codelong.thenewboston.factory.ResponseFactory
import tv.codelong.thenewboston.model.Role
import tv.codelong.thenewboston.repository.UserRepo
import tv.codelong.thenewboston.service.user.UserService

@Service
class AuthService (
    private val userService: UserService,
    private val tokenService: TokenService,
    private val userRepo: UserRepo,
    private val hashService: HashService,
    private val exceptionFactory: ResponseFactory
){
    fun addUser(registerDto: RegisterDto): ResponseDto<RegisterDto> {
        val user = registerDto.toUser()
        return if (
            userService.existsByName(registerDto.name.trim()) ||
            userService.existsByPhone(registerDto.userPhone.trim()) ||
            userService.existsByMail(registerDto.userMail.trim())
            ){
            throw ApiException(
                resultCode = ResultCode.ValidationError,
                message = "User already exists."
            )
        }else if(registerDto.password != registerDto.confirmPassword){
            throw ApiException(
                resultCode = ResultCode.ValidationError,
                message = "Passwords do not match."
            )
        }
        else{
            user.password = hashService.hashBcrypt(user.password)
            user.role = Role.USER
            val savedUser = userRepo.save(user)
            ResponseDto(resultCode = ResultCode.Success, data = RegisterDto(
                id = savedUser.id,
                name = savedUser.name,
                userName = savedUser.userName,
                userSurname = savedUser.userSurname,
                userMail = savedUser.userMail,
                userPhone = savedUser.userPhone,
                password = savedUser.password,
            ))
        }
    }

    fun loginUser(payload: LoginDto) : ResponseDto<LoginResponseDto>{
        val user = userService.findByName(payload.name)
            ?: throw UserNotFoundException()
        if (!hashService.checkBcrypt(payload.password, user.password)){
            throw ApiException(
                resultCode = ResultCode.ValidationError,
                message = "Login failed. Incorrect username or password."
            )
        }
        return ResponseDto(
            resultCode = ResultCode.Success,
            data = LoginResponseDto(token = tokenService.createToken(user), role = user.role)
        )
    }
}
