package tv.codelong.thenewboston.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.Lob
import jakarta.persistence.ManyToOne
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import org.hibernate.annotations.JdbcTypeCode
import java.sql.Types

@Entity
@Table(name = "news")
data class News(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "news_id_seq")
    @SequenceGenerator(name = "news_id_seq", allocationSize = 1)
    @Column(name = "id")
    var id: Long = 0,

    @Column(name = "header")
    var header: String,

    @Column(name = "content")
    var content: String,

    @Column(name = "media_file", columnDefinition = "BLOB")
    @Lob
    @JdbcTypeCode(Types.VARBINARY)
    var imgFile: ByteArray? = null,

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    var user: User,
)
