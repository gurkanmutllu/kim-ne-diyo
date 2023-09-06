package tv.codelong.thenewboston.service.question.dtos

import tv.codelong.thenewboston.model.Test
import tv.codelong.thenewboston.service.test.dtos.ShortTestDTO

data class AddQuestionDto (
    val questionText: String,
    val answers: MutableCollection<AddAnswerDto>,
    val test: ShortTestDTO?,
)
