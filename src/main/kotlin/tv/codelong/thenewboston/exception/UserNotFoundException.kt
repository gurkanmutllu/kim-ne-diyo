package tv.codelong.thenewboston.exception

class UserNotFoundException (
    override val resultCode: ResultCode = ResultCode.NotFound,
    override val message: String = "User not found!"
) : AppException(resultCode, message)
