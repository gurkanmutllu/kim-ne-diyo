package tv.codelong.thenewboston.service

import org.springframework.stereotype.Service
import java.util.*

@Service
class Base64Service {

    companion object {
        fun base64ToByteArray(base64: String?): ByteArray? {
            if (base64 != null) {
                return Base64.getDecoder().decode(base64)
            }
            return null
        }

        fun byteArrayToBase64(byteArray: ByteArray?): String? {
            return if (byteArray != null) {
                Base64.getEncoder().encodeToString(byteArray)
            } else {
                null
            }
        }

    }
}