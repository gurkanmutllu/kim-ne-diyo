package tv.codelong.thenewboston.repository

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.repository.CrudRepository
import tv.codelong.thenewboston.model.User
import org.springframework.stereotype.Repository

//@Qualifier("user")
@Repository
interface UserRepo : CrudRepository<User, Long> {
    fun findByName(name: String): User?
    fun existsByName(name: String): Boolean
    fun existsByUserMail(userMail: String): Boolean
    fun existsByUserPhone(userPhone: String): Boolean
    //  abstract fun save(): User
}
