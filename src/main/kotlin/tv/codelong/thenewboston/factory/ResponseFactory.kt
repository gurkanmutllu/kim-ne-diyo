package tv.codelong.thenewboston.factory

import org.springframework.stereotype.Service
import tv.codelong.thenewboston.dto.ResponseDto
import tv.codelong.thenewboston.exception.*

@Service
class ResponseFactory {

    fun <T> success (
        code: ResultCode = ResultCode.Success,
        message: String? = null,
        data: T? = null
    ): ResponseDto <T> {
        return ResponseDto(
            resultCode = code,
            message = message,
            data = data
        )
    }

    fun <T> error(
        code: ResultCode = ResultCode.InternalServerError,
        message: String,
    ): ResponseDto <T> {
        return ResponseDto(
            resultCode = code,
            message = message
        )
    }
}
