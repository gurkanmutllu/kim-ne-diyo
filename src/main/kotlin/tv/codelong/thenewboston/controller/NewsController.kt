package tv.codelong.thenewboston.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tv.codelong.thenewboston.service.news.dtos.AddNewsDto
import tv.codelong.thenewboston.service.news.dtos.NewsDto
import tv.codelong.thenewboston.dto.ResponseDto
import tv.codelong.thenewboston.exception.ResultCode
import tv.codelong.thenewboston.factory.ResponseFactory
import tv.codelong.thenewboston.model.News
import tv.codelong.thenewboston.service.news.NewsService
import tv.codelong.thenewboston.service.news.dtos.UpdateNewsDto

@RestController
@RequestMapping("/api/news")
class NewsController (
    private val newsService: NewsService,
    private val responseFactory: ResponseFactory
){
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @PostMapping("/add")
    fun addNews(@RequestBody payload: AddNewsDto): ResponseDto<Nothing> {
        newsService.addNews(payload)
        return responseFactory.success()
    }

    @GetMapping("/get-by-id/{id}")
    fun getNewsById(@PathVariable id: Long): ResponseDto<NewsDto>{
        return newsService.findById(id)
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @PutMapping("/update")
    fun updateNews(@RequestBody newsDto: UpdateNewsDto): ResponseDto<Nothing> {
        newsService.updateNews(newsDto)
        return responseFactory.success()
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @DeleteMapping("/delete/{id}")
    fun deleteNews(@PathVariable id: Long): ResponseDto<Nothing> {
        newsService.deleteNews(id)
        return responseFactory.success()
    }

    @GetMapping("/get-all")
    fun getAllNews(): ResponseDto<MutableList<NewsDto>> {
        return ResponseDto(
            resultCode = ResultCode.Success,
            message = "List of all news:",
            data = newsService.getAllNews()
        )
    }
}
