package tv.codelong.thenewboston.service

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import tv.codelong.thenewboston.dto.ResponseDto
import tv.codelong.thenewboston.service.user.dtos.UserDto

@Service
class SecurityService {

    fun getLoggedInUser(): UserDto? {
        val response = SecurityContextHolder.getContext().authentication?.principal as? ResponseDto<*>
        return response?.data as UserDto
    }
}
//    fun getLoggedInUser(): User? {
//        val authentication = SecurityContextHolder.getContext().authentication
//        if (authentication != null && authentication.isAuthenticated) {
//            return authentication.principal as? User
//        }
//        throw ApiException(
//            resultCode = ResultCode.NotFound,
//            message = "User was not logged in."
//        )
//    }

