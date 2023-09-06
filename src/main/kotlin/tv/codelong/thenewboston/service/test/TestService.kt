package tv.codelong.thenewboston.service.test

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import tv.codelong.thenewboston.dto.ResponseDto
import tv.codelong.thenewboston.service.test.dtos.AddTestDto
import tv.codelong.thenewboston.dto.toDto
import tv.codelong.thenewboston.exception.ApiException
import tv.codelong.thenewboston.exception.ResultCode
import tv.codelong.thenewboston.exception.TestNotFoundException
import tv.codelong.thenewboston.exception.UserNotFoundException
import tv.codelong.thenewboston.model.Answer
import tv.codelong.thenewboston.model.Question
import tv.codelong.thenewboston.model.Role
import tv.codelong.thenewboston.model.Test
import tv.codelong.thenewboston.repository.AnswerRepo
import tv.codelong.thenewboston.repository.QuestionRepo
import tv.codelong.thenewboston.repository.TestRepo
import tv.codelong.thenewboston.repository.UserRepo
import tv.codelong.thenewboston.service.SecurityService
import tv.codelong.thenewboston.service.question.dtos.AddQuestionDto
import tv.codelong.thenewboston.service.test.dtos.TestDto
import tv.codelong.thenewboston.service.test.dtos.UpdateTestDTO


@Service
class TestService(
    private val securityService: SecurityService,
    private val testRepo: TestRepo,
    private val questionRepo: QuestionRepo,
    private val userRepo: UserRepo,
    private val answerRepo: AnswerRepo
) {
    fun addTest(testDto: AddTestDto) {
        val testUser = userRepo.findByIdOrNull(testDto.user)
            ?: throw UserNotFoundException()

        testRepo.save(
            Test(
                testName = testDto.testName,
                content = testDto.content,
                mediaFile = testDto.mediaFile,
                user = testUser
            )
        )
    }

    fun findById(id: Long): ResponseDto<TestDto> {
        val test = testRepo.findByIdOrNull(id)
            ?: throw TestNotFoundException(
                message = "Test not found with id: $id"
            )
        return ResponseDto(
            resultCode = ResultCode.Success,
            message = "Test with id: ${test.id}",
            data = TestDto.create(test)
        )
    }

    fun updateTest(testDto: UpdateTestDTO) {
        val loggedInUser = securityService.getLoggedInUser()
            ?: throw UserNotFoundException()

        testRepo.findByIdOrNull(testDto.id)?.let {
            if (loggedInUser.id != it.user.id) {
                throw ApiException(
                    resultCode = ResultCode.Forbidden,
                    message = "No permission to change this test."
                )
            } else {
                it.testName = testDto.testName ?: it.testName
                it.content = testDto.content ?: it.content
                it.mediaFile = testDto.mediaFile ?: it.mediaFile
                testRepo.save(it)
            }
        } ?: throw TestNotFoundException()
    }

    fun deleteTest(id: Long) {
        val loggedInUser = securityService.getLoggedInUser()
            ?: throw UserNotFoundException()

        testRepo.findByIdOrNull(id)?.let {
            if (loggedInUser.id != it.user.id ||
                loggedInUser.role !in listOf(Role.USER, Role.ADMIN)
            ) {
                throw ApiException(
                    resultCode = ResultCode.Forbidden,
                    message = "No permission to delete this test."
                )
            } else {
                testRepo.deleteById(it.id)
            }
        } ?: throw TestNotFoundException()
    }

    fun getAllTests(): MutableList<AddTestDto> {
        val tests = testRepo.findAll()
        return tests.map { it.toDto() }.toMutableList()
    }

    fun addQuestionToTest(id: Long, addQuestionDto: AddQuestionDto) {
        val test = testRepo.findByIdOrNull(id)
            ?: throw TestNotFoundException()

        val savedQuestion = questionRepo.save(
            Question(
                questionText = addQuestionDto.questionText,
                answers = mutableListOf(),
                test = test
            )
        )

        for (i in addQuestionDto.answers) {
            answerRepo.save(
                Answer(
                    answerText = i.answerText,
                    isCorrect = i.isCorrect ?: false,
                    question = savedQuestion
                    )
            )
        }
    }

    fun getAllQuestionsForTest(id: Long): MutableList<AddQuestionDto> {
        val questions = questionRepo.findByTestId(id)
        return questions?.map { it }?.toMutableList() ?: throw TestNotFoundException()
    }
}
