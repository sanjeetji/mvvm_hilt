package com.sanjeet.simpletrancsaction.view.login

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sanjeet.simpletrancsaction.data.remote.TransactionApi
import com.sanjeet.simpletrancsaction.data.repository.UserLoginImpl
import com.sanjeet.simpletrancsaction.domain.repository.UserLoginRepository
import com.sanjeet.simpletrancsaction.domain.usecases.UserLoginUseCase
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserLoginViewModelTest {

    lateinit var viewModel: UserLoginViewModel
    lateinit var userLoginUseCase: UserLoginUseCase
    lateinit var userLoginRepository: UserLoginRepository
    lateinit var userLoginImpl: UserLoginImpl
    lateinit var transactionApi: TransactionApi

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        userLoginUseCase = UserLoginUseCase(userLoginRepository)
        viewModel = UserLoginViewModel(userLoginUseCase)
        val data = viewModel.userLogin("morty","smith")
    }

    @After
    fun tearDown() {
    }
}