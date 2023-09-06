package tv.codelong.thenewboston.service.user

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import tv.codelong.thenewboston.dto.ResponseDto
import tv.codelong.thenewboston.service.user.dtos.UpdateUserDto
import tv.codelong.thenewboston.service.user.dtos.UserDto
import tv.codelong.thenewboston.dto.toDto
import tv.codelong.thenewboston.exception.ApiException
import tv.codelong.thenewboston.exception.ResultCode
import tv.codelong.thenewboston.exception.UserNotFoundException
import tv.codelong.thenewboston.model.User
import tv.codelong.thenewboston.repository.UserRepo
import tv.codelong.thenewboston.service.HashService
import tv.codelong.thenewboston.service.SecurityService

@Service
class UserService(
    private val userRepo: UserRepo,
    private val hashService: HashService,
    private val securityService: SecurityService,
) {
    fun findById(id: Long): ResponseDto<UserDto> {
        val user = userRepo.findByIdOrNull(id)
            ?: throw UserNotFoundException()
        return ResponseDto(
            resultCode = ResultCode.Success,
            message = "User with id: $id",
            data = UserDto.create(user)
        )
    }

    fun updateUser(userDto: UpdateUserDto) {
        val loggedInUser = securityService.getLoggedInUser()
            ?: throw UserNotFoundException()

        userRepo.findByIdOrNull(userDto.id)?.let {
            if (loggedInUser.id != userDto.id){
                throw ApiException(
                    resultCode = ResultCode.Forbidden,
                    message = "No permission to change another user."
                )
            }else {
                it.name = userDto.name ?: it.name
                it.userName = userDto.userName ?: it.userName
                it.userSurname = userDto.userSurname ?: it.userSurname
                it.userMail = userDto.userMail ?: it.userMail
                it.userPhone = userDto.userPhone ?: it.userPhone
                userRepo.save(it)
            }
        } ?: throw UserNotFoundException()
    }

    fun deleteUser(id: Long) {
        val loggedInUser = securityService.getLoggedInUser()
            ?: throw UserNotFoundException()
        userRepo.findByIdOrNull(id)?.let {
            if (loggedInUser.id != it.id) {
                throw ApiException(
                    resultCode = ResultCode.Forbidden,
                    message = "No permission to delete this user."
                )
            }else {
                userRepo.deleteById(it.id)
            }
        }?: throw UserNotFoundException()
    }

    //MUTABLE List **
    fun getAllUsers(): MutableList<UserDto> {
        val users = userRepo.findAll()
        return users.map { UserDto.create(it) }.toMutableList()
    }

    fun findByName(name: String): User? {
        return userRepo.findByName(name)
    }

    fun existsByName(name: String): Boolean {
        return userRepo.existsByName(name)
    }

    fun existsByMail(userMail: String): Boolean{
        return userRepo.existsByUserMail(userMail)
    }
    fun existsByPhone(userPhone: String): Boolean{
        return userRepo.existsByUserPhone(userPhone)
    }

    fun updatePassword(id: Long, newPassword: String) {
        val user = userRepo.findByIdOrNull(id) ?: throw UserNotFoundException(ResultCode.NotFound)
        user.password = hashService.hashBcrypt(newPassword)
        userRepo.save(user)
    }
}
