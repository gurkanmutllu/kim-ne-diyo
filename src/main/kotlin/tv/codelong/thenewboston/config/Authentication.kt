package tv.codelong.thenewboston.config

import org.springframework.security.core.Authentication
import tv.codelong.thenewboston.model.User

/**
 * Shorthand for controllers accessing the authenticated user.
 */
fun Authentication.toUser(): User {
    return principal as User
}
