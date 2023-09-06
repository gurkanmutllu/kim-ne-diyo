package tv.codelong.thenewboston.exception

class ApiException (
    override val resultCode: ResultCode,
    override val message: String
) : AppException(resultCode, message)
