package tv.codelong.thenewboston.controller

import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import tv.codelong.thenewboston.config.toUser
import tv.codelong.thenewboston.dto.*
import tv.codelong.thenewboston.exception.ApiException
import tv.codelong.thenewboston.exception.ResultCode
import tv.codelong.thenewboston.model.Item
import tv.codelong.thenewboston.service.ItemService

/**
 * This controller handles requests for managing items of the authenticated user.
 */
@RestController
@RequestMapping("/api/items")
class ItemController(
        private val itemService: ItemService,
) {

    @GetMapping
    fun getItems(authentication: Authentication): List<ItemDto> {
        val authUser = authentication.toUser()

        return itemService.findByUser(authUser).map { item -> item.toDto() }
    }

    @PostMapping
    fun createItem(authentication: Authentication, @RequestBody payload: CreateItemDto) {
        val authUser = authentication.toUser()

        if (itemService.existsByNameAndUser(payload.name, authUser)) {
            throw ApiException(ResultCode.Conflict, "Item name already exists")
        }

        val item = Item(
                user = authUser,
                name = payload.name,
                count = payload.count,
                note = payload.note,
        )

        itemService.save(item)
    }

    @PutMapping
    fun updateItem(authentication: Authentication, @RequestBody payload: UpdateItemDto) {
        val authUser = authentication.toUser()

        val item = itemService.findById(payload.id) ?: throw ApiException(ResultCode.NotFound, "Item not found")
        if (item.user.id != authUser.id) {
            throw ApiException(ResultCode.Forbidden, "Not your item")
        }

        val existingItem = itemService.findByNameAndUser(payload.name, authUser)
        if (existingItem != null && existingItem.id != payload.id) {
            throw ApiException(ResultCode.Conflict, "Item name already exists")
        }

        item.name = payload.name
        item.count = payload.count
        item.note = payload.note

        itemService.save(item)
    }

    @DeleteMapping
    fun deleteItem(authentication: Authentication, @RequestParam itemId: Long) {
        val authUser = authentication.toUser()
        val item = itemService.findById(itemId) ?: throw ApiException(ResultCode.NotFound, "Item not found")
        if (item.user.id != authUser.id) {
            throw ApiException(ResultCode.Forbidden, "Not your item")
        }

        itemService.delete(item)
    }
}
