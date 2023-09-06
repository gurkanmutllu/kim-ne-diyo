package tv.codelong.thenewboston.repository

import org.springframework.data.repository.CrudRepository
import tv.codelong.thenewboston.model.Item
import tv.codelong.thenewboston.model.User

interface ItemRepo : CrudRepository<Item, Long> {
    fun findByUser(user: User): List<Item>
    fun findByNameAndUser(name: String, user: User): Item?

    fun existsByNameAndUser(name: String, user: User): Boolean
}
