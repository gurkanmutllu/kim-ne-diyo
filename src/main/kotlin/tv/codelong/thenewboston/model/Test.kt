package tv.codelong.thenewboston.model

import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import java.sql.Types

@Entity
@Table(name = "tests")
data class Test (

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "news_id_seq")
    @SequenceGenerator(name = "news_id_seq", allocationSize = 1)
    @Column(name = "id")
    var id: Long = 0,

    @Column(name = "test_name")
    var testName: String,

    @Column(name = "content")
    var content: String,

    @Column(name = "media_file", columnDefinition = "BLOB")
    @Lob
    @JdbcTypeCode(Types.VARBINARY)
    var mediaFile: ByteArray? = null,

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    var user: User,
)
