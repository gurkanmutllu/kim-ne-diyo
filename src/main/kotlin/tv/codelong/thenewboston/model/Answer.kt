package tv.codelong.thenewboston.model

import jakarta.persistence.*

@Entity
@Table(name = "answers")
data class Answer (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "news_id_seq")
    @SequenceGenerator(name = "news_id_seq", allocationSize = 1)
    @Column(name = "id")
    var id: Long = 0,

    @Column(name = "answer_text")
    var answerText: String,

    @Column(name = "is_correct")
    var isCorrect: Boolean,

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    var question: Question,
)
