package com.sanjeet.simpletrancsaction.domain.usecases

import com.sanjeet.simpletrancsaction.common.Resource
import com.sanjeet.simpletrancsaction.domain.repository.UserLoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserLoginUseCase @Inject constructor(private val repository: UserLoginRepository) {

    operator fun invoke(userName: String, password: String): Flow<Resource<Int>> = flow {
        try {
            emit(Resource.Loading())

            val response = repository.loginUser(userName, password)
            val code = if (!response.code().equals(200)) 0 else response.code()
            emit(Resource.LoginSuccess(data = code))

        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Check your internet connection"))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "UnExpected Error"))
        }
    }
}