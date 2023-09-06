package tv.codelong.thenewboston.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tv.codelong.thenewboston.dto.ResponseDto
import tv.codelong.thenewboston.service.test.dtos.AddTestDto
import tv.codelong.thenewboston.exception.ResultCode
import tv.codelong.thenewboston.factory.ResponseFactory
import tv.codelong.thenewboston.service.question.dtos.AddQuestionDto
import tv.codelong.thenewboston.service.test.TestService
import tv.codelong.thenewboston.service.test.dtos.TestDto
import tv.codelong.thenewboston.service.test.dtos.UpdateTestDTO


@RestController
@RequestMapping("api/tests")
class TestController(
    private val testService: TestService,
    private val responseFactory: ResponseFactory
) {

    @PostMapping("/add")
    fun addTest(@RequestBody payload: AddTestDto): ResponseDto<Nothing> {
        testService.addTest(payload)
        return responseFactory.success()
    }

    @GetMapping("/get-by-id/{id}")
    fun getTestById(@PathVariable id: Long): ResponseDto<TestDto> {
        return testService.findById(id)
    }

    @PutMapping("/update")
    fun updateTest(@RequestBody testDto: UpdateTestDTO): ResponseDto<Nothing> {
        testService.updateTest(testDto)
        return responseFactory.success()
    }

    @DeleteMapping("/delete/{id}")
    fun deleteTest(@PathVariable id: Long): ResponseDto<Nothing> {
        testService.deleteTest(id)
        return responseFactory.success()
    }

    @GetMapping("/get-all")
    fun getAllTests(): ResponseDto<MutableList<AddTestDto>> {
        return ResponseDto(
            resultCode = ResultCode.Success,
            data = testService.getAllTests()
        )
    }

    @PostMapping("/{id}/add-question")
    fun addQuestionToTest(@PathVariable id: Long, @RequestBody question: AddQuestionDto): ResponseDto<Nothing> {
        testService.addQuestionToTest(id, question)
        return responseFactory.success()
    }

    @GetMapping("/{testId}/get-all-questions")
    fun getAllQuestionsForTest(@PathVariable testId: Long): ResponseDto<MutableList<AddQuestionDto>> {
        return ResponseDto(
            resultCode = ResultCode.Success,
            data = testService.getAllQuestionsForTest(testId)
        )
    }
}
