package com.sanjeet.mealsearchapp.hilt

import com.sanjeet.mealsearchapp.common.Constants
import com.sanjeet.simpletrancsaction.data.remote.TransactionApi
import com.sanjeet.simpletrancsaction.data.repository.HomeRepoImpl
import com.sanjeet.simpletrancsaction.data.repository.UserLoginImpl
import com.sanjeet.simpletrancsaction.domain.repository.HomeRepository
import com.sanjeet.simpletrancsaction.domain.repository.UserLoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModules {

    @Provides
    @Singleton
    fun provideTransactionApi(): TransactionApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(TransactionApi::class.java)
    }

    @Provides
    fun provideUserLoginRepository(transactionApi: TransactionApi): UserLoginRepository {
        return UserLoginImpl(transactionApi)
    }

    @Provides
    fun provideHomeRepository(transactionApi: TransactionApi): HomeRepository {
        return HomeRepoImpl(transactionApi)
    }


}