package tv.codelong.thenewboston.repository

import org.springframework.data.repository.CrudRepository
import tv.codelong.thenewboston.model.Test

interface TestRepo : CrudRepository<Test, Long> {
    fun findByTestName(testName: String): Test?
    fun existsByTestName(testName: String): Boolean
}
