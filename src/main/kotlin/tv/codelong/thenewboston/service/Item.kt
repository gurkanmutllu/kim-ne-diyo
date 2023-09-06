package tv.codelong.thenewboston.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import tv.codelong.thenewboston.model.Item
import tv.codelong.thenewboston.model.User
import tv.codelong.thenewboston.repository.ItemRepo

@Service
class ItemService(
        private val itemRepo: ItemRepo,
) {
    fun findById(id: Long): Item? {
        return itemRepo.findByIdOrNull(id)
    }

    fun findByUser(user: User): List<Item> {
        return itemRepo.findByUser(user)
    }

    fun findByNameAndUser(name: String, user: User): Item? {
        return itemRepo.findByNameAndUser(name, user)
    }

    fun existsByNameAndUser(name: String, user: User): Boolean {
        return itemRepo.existsByNameAndUser(name, user)
    }

    fun save(item: Item): Item {
        return itemRepo.save(item)
    }

    fun delete(item: Item) {
        return itemRepo.delete(item)
    }
}
