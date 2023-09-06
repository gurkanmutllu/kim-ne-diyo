package tv.codelong.thenewboston.model

import jakarta.persistence.*

@Entity
@Table(name = "questions")
data class Question (

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "news_id_seq")
    @SequenceGenerator(name = "news_id_seq", allocationSize = 1)
    @Column(name = "id")
    var id: Long = 0,

    @Column(name = "question_text")
    var questionText: String,

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    var answers: MutableCollection<Answer> = mutableListOf(),

    @ManyToOne
    @JoinColumn(name = "test_id", referencedColumnName = "id")
    var test: Test,

    )

