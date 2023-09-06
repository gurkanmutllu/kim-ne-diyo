package tv.codelong.thenewboston

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/users")
@CrossOrigin
class HelloWorldController {
    @GetMapping("/hello",
            produces = ["application/json"])
    fun helloWorld(): ResponseEntity<String> =
            ResponseEntity<String>(
                    "{\"text\": \"hello this is a rest endpoint\", \"message\": \"mesajjjj\"}",
                    HttpStatus.OK
            )

}
