package tv.codelong.thenewboston.service.news

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import tv.codelong.thenewboston.dto.*
import tv.codelong.thenewboston.exception.*
import tv.codelong.thenewboston.model.News
import tv.codelong.thenewboston.repository.NewsRepo
import tv.codelong.thenewboston.repository.UserRepo
import tv.codelong.thenewboston.service.Base64Service
import tv.codelong.thenewboston.service.SecurityService
import tv.codelong.thenewboston.service.news.dtos.AddNewsDto
import tv.codelong.thenewboston.service.news.dtos.NewsDto
import tv.codelong.thenewboston.service.news.dtos.UpdateNewsDto

@Service
class NewsService(
    private val newsRepo: NewsRepo,
    private val securityService: SecurityService,
    private val userRepo: UserRepo
) {
    fun addNews(newsDto: AddNewsDto) {
        val newsUser = userRepo.findByIdOrNull(newsDto.user)
            ?: throw UserNotFoundException()

        newsRepo.save(News(
            header = newsDto.header,
            content = newsDto.content,
            imgFile = newsDto.imgFile?.bytes,
            user = newsUser
        ))
}

    fun findById(id: Long): ResponseDto<NewsDto> {
        val news = newsRepo.findByIdOrNull(id)
            ?: throw NewsNotFoundException()
        return ResponseDto(
            resultCode = ResultCode.Success,
            message = "News with id: ${news.id}",
            data = NewsDto.create(news)
        )
    }

    //if loggedinuser != news.user.id
    //return forbidden
    fun updateNews(newsDto: UpdateNewsDto) {
        val loggedInUser = securityService.getLoggedInUser()
            ?: throw UserNotFoundException()

        newsRepo.findByIdOrNull(newsDto.id)?.let {
            if (loggedInUser.id != it.user.id){
                throw ApiException(
                    resultCode = ResultCode.Forbidden,
                    message = "No permission to change this news."
                )
            }else {
                it.header = newsDto.header
                it.content = newsDto.content
                it.imgFile = newsDto.imgFile?.bytes
                newsRepo.save(it)
            }
        }?: throw NewsNotFoundException()
    }

    fun deleteNews(id: Long) {
//        if loggedinuser != news.user.id || loggedInuser.role !in Admin, Moderator
//        return forbidden
        val loggedInUser = securityService.getLoggedInUser()
            ?: throw UserNotFoundException()

        newsRepo.findByIdOrNull(id)?.let {
            if (loggedInUser.id != it.user.id) {
                throw ApiException(
                    resultCode = ResultCode.Forbidden,
                    message = "No permission to delete this news."
                )
            }else{
                newsRepo.deleteById(it.id)
            }
        } ?: throw NewsNotFoundException()
    }

    fun getAllNews(): MutableList<NewsDto> {
        val news = newsRepo.findAll()
        return news.map { NewsDto.create(it) }.toMutableList()
    }
}
