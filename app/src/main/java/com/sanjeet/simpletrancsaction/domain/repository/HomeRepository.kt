package com.sanjeet.simpletrancsaction.domain.repository

import com.sanjeet.simpletrancsaction.data.model.AccountsInfoDTO
import com.sanjeet.simpletrancsaction.data.model.TransactionsDTO


interface HomeRepository {

    suspend fun getAccounts(): List<AccountsInfoDTO>

    suspend fun getTransactions(accountId: String): List<TransactionsDTO>
}