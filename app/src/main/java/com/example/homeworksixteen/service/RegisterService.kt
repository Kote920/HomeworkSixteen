package com.example.homeworksixteen.service

import com.example.homeworksixteen.responses.ResponseRegister
import com.example.homeworksixteen.Request
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {

    @POST("register")
    suspend fun register(@Body request: Request): Response<ResponseRegister>

}