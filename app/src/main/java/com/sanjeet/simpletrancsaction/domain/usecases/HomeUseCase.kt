package com.sanjeet.simpletrancsaction.domain.usecases

import com.sanjeet.simpletrancsaction.common.Resource
import com.sanjeet.simpletrancsaction.data.model.toDomainAccountDetails
import com.sanjeet.simpletrancsaction.data.model.toDomainTransactionDetails
import com.sanjeet.simpletrancsaction.domain.model.AccountDetails
import com.sanjeet.simpletrancsaction.domain.model.TransactionDetails
import com.sanjeet.simpletrancsaction.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val homeRepository: HomeRepository) {

    operator fun invoke(): Flow<Resource<List<AccountDetails>>> = flow {
        try {
            emit(Resource.Loading())
            val response = homeRepository.getAccounts()
            response.let {
                val data = response.map { it.toDomainAccountDetails() }
                emit(Resource.Success(data = data))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Check your internet connection"))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "UnExpected Error"))
        }
    }

    operator fun invoke(accountId: String): Flow<Resource<List<TransactionDetails>>> = flow {
        try {
            emit(Resource.Loading())
            val response = homeRepository.getTransactions(accountId)
            response.let {
                val data = response.map { it.toDomainTransactionDetails() }
                emit(Resource.Success(data = data))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Unknown Error"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Check your internet connection"))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "UnExpected Error"))
        }
    }
}