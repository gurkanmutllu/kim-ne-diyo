package tv.codelong.thenewboston.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import tv.codelong.thenewboston.model.Question
import tv.codelong.thenewboston.model.User
import tv.codelong.thenewboston.service.question.dtos.AddQuestionDto

@Repository
interface QuestionRepo : CrudRepository<Question, Long>{

    fun findByTestId(testId: Long): MutableCollection<AddQuestionDto>?
}