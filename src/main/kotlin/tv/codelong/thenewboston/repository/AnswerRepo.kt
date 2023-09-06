package tv.codelong.thenewboston.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import tv.codelong.thenewboston.model.Answer

@Repository
interface AnswerRepo: CrudRepository<Answer, Long> {
}