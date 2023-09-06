package tv.codelong.thenewboston.dto

import tv.codelong.thenewboston.model.*
import tv.codelong.thenewboston.service.Base64Service
import tv.codelong.thenewboston.service.news.dtos.NewsDto
import tv.codelong.thenewboston.service.question.dtos.AddAnswerDto
import tv.codelong.thenewboston.service.test.dtos.AddTestDto
import tv.codelong.thenewboston.service.user.dtos.ShortUserDTO
import tv.codelong.thenewboston.service.user.dtos.UserDto

fun Item.toDto(): ItemDto {
    return ItemDto(id, name, count, note)
}

fun User.toDto(): UserDto {
    return UserDto(
        id = id,
        name = name,
        userName = userName,
        userSurname = userSurname,
        userMail = userMail,
        userPhone = userPhone,
        role = role
    )
}

fun News.toDto(): NewsDto {
    return NewsDto(id = id, header = header, content = content, imgFile = Base64Service.byteArrayToBase64(imgFile), user = user.id)
}

fun UserDto.toUser(): User {
    return User(name = name, userName = userName, userSurname = userSurname, userMail = userMail, userPhone = userPhone)
}

fun Test.toDto(): AddTestDto {
    return AddTestDto(testName = testName, content = content, mediaFile = mediaFile, user = user.id)
}

//fun AddTestDto.toTest(): Test {
//    return Test(testName = testName, content = content, mediaFile = mediaFile, user = user.id)
//}

fun ShortUserDTO.toUser(): User {
    return User(id = id, name = name, userName = userName, userSurname = userSurname)
}

//fun AddNewsDto.toNews(): News {
//   return News(header = header, content = content, imgFile = imgFile, userId = userId)
//
//}
fun RegisterDto.toUser(): User {
    return User(
        name = name,
        userName = userName,
        userSurname = userSurname,
        userMail = userMail,
        userPhone = userPhone,
        password = password
    )
}
