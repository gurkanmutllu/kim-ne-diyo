package tv.codelong.thenewboston.exception

class NewsNotFoundException (
    override val resultCode: ResultCode = ResultCode.NotFound,
    override val message: String = "News not found!"
) : AppException(resultCode, message)
