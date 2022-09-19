package com.sanjeet.simpletrancsaction.domain.repository

import okhttp3.ResponseBody
import retrofit2.Response


interface UserLoginRepository {

    suspend fun loginUser(userName: String, password: String): Response<ResponseBody>
}