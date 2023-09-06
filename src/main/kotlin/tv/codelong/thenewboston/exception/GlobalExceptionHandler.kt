package tv.codelong.thenewboston.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import tv.codelong.thenewboston.dto.ResponseDto
import tv.codelong.thenewboston.factory.ResponseFactory

//@Service
//@RestControllerAdvice
//class GlobalExceptionHandler(private val exceptionFactory: ExceptionFactory) {
//
//    @ExceptionHandler
//    fun handleException(e: Exception): ResponseDto<Any> {
//        val appException = exceptionFactory.createException(e)
//        return ResponseDto(appException.resultCode, appException.message)
//    }
//}

@Service
@RestControllerAdvice
class GlobalExceptionHandler(private val responseFactory: ResponseFactory) {

    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFoundException(ex: UserNotFoundException): ResponseDto<Nothing> {
        return responseFactory.error(code = ex.resultCode, message = ex.message)
    }

    @ExceptionHandler(NewsNotFoundException::class)
    fun handleNewsNotFoundException(ex: NewsNotFoundException): ResponseDto<Nothing> {
        return responseFactory.error(code = ex.resultCode, message = ex.message)    }

    @ExceptionHandler(ApiException::class)
    fun handleApiException(ex: ApiException): ResponseDto<Nothing> {
        return responseFactory.error(code = ex.resultCode, message = ex.message)    }

    @ExceptionHandler(TestNotFoundException::class)
    fun handleTestNotFoundException(ex: TestNotFoundException): ResponseDto<Nothing> {
        return responseFactory.error(code = ex.resultCode, message = ex.message)
    }
}

