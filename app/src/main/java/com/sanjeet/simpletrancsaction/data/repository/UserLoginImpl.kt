package com.sanjeet.simpletrancsaction.data.repository

import com.sanjeet.simpletrancsaction.data.remote.TransactionApi
import com.sanjeet.simpletrancsaction.domain.repository.UserLoginRepository
import okhttp3.ResponseBody
import retrofit2.Response

class UserLoginImpl(private val transactionApi: TransactionApi) : UserLoginRepository {

    override suspend fun loginUser(userName: String, password: String): Response<ResponseBody> {
        return transactionApi.loginUser(userName, password)
    }

}