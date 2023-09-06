package tv.codelong.thenewboston.dto

import tv.codelong.thenewboston.exception.ResultCode

data class ResponseDto <T>(
    val resultCode: ResultCode,
    val message: String? = null,
    val data: T? = null
)
