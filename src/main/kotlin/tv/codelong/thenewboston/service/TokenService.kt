package tv.codelong.thenewboston.service

import org.springframework.security.oauth2.jwt.*
import org.springframework.stereotype.Service
import tv.codelong.thenewboston.dto.ResponseDto
import tv.codelong.thenewboston.service.user.dtos.UserDto
import tv.codelong.thenewboston.exception.ResultCode
import tv.codelong.thenewboston.model.User
import tv.codelong.thenewboston.service.user.UserService
import java.time.Instant
import java.time.temporal.ChronoUnit

/**
 * This service creates and parses tokens.
 * It will do database operations.
 */
@Service
class TokenService(
    private val jwtDecoder: JwtDecoder,
    private val jwtEncoder: JwtEncoder,
    private val userService: UserService,
) {
    fun createToken(user: User): String {
        val jwsHeader = JwsHeader.with { "HS256" }.build()
        val claims = JwtClaimsSet.builder()
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(30L, ChronoUnit.DAYS))
                .subject(user.name)
                .claim("userId", user.id)
                .build()
        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).tokenValue
    }

    fun parseToken(token: String): ResponseDto<UserDto> {
        return try {
            val jwt = jwtDecoder.decode(token)
            val userId = jwt.claims["userId"] as Long
            userService.findById(userId)
        } catch (e: Exception) {
            return ResponseDto(
                resultCode = ResultCode.NotFound
            )
        }
    }
}
