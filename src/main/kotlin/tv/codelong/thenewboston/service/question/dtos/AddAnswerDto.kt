package tv.codelong.thenewboston.service.question.dtos

import tv.codelong.thenewboston.model.Answer
import tv.codelong.thenewboston.model.Question

data class AddAnswerDto (
    val answerText: String,
    val isCorrect: Boolean ?= false,
    val question: Question?
) {
    companion object {
        fun create(answer: Answer) = AddAnswerDto (
            answerText = answer.answerText,
            isCorrect = answer.isCorrect,
            question = answer.question
        )
    }
}