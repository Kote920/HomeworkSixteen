package com.example.homeworksixteen.service

import com.example.homeworksixteen.Request
import com.example.homeworksixteen.responses.ResponseLogIn
import com.example.homeworksixteen.responses.ResponseRegister
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LogInService {

    @POST("login")
    suspend fun logIn(@Body request: Request): Response<ResponseLogIn>

}