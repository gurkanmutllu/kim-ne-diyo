package tv.codelong.thenewboston.exception

open class AppException(
    open val resultCode: ResultCode,
    message: String
) : RuntimeException(message)
