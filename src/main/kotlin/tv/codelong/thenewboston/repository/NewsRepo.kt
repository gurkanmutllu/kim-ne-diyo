package tv.codelong.thenewboston.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import tv.codelong.thenewboston.model.News

@Repository
interface NewsRepo: CrudRepository<News, Long> {
    fun findByHeader(header: String): News?
    fun existsByHeader(header: String): Boolean

}
