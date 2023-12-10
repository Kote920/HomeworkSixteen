package com.example.homeworksixteen.responses

import com.squareup.moshi.Json

data class ResponseLogIn(@Json(name = "token") var token: String? = null)