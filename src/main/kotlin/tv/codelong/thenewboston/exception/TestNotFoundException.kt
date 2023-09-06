package tv.codelong.thenewboston.exception

class TestNotFoundException (
    override val resultCode: ResultCode = ResultCode.NotFound,
    override val message: String = "Test not found!"
) : AppException(resultCode, message)
