package com.sanjeet.simpletrancsaction.data.remote

import com.sanjeet.simpletrancsaction.data.model.AccountsInfoDTO
import com.sanjeet.simpletrancsaction.data.model.TransactionsDTO
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface TransactionApi {


    @FormUrlEncoded
    @POST("login")
    suspend fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<ResponseBody>

    @GET("accounts")
    suspend fun getAccounts(): List<AccountsInfoDTO>

    @GET("transactions")
    suspend fun getTransactions(@Query("accountId") accountId: String): List<TransactionsDTO>
}