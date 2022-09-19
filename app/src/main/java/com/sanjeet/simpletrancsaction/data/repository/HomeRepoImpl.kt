package com.sanjeet.simpletrancsaction.data.repository

import com.sanjeet.simpletrancsaction.data.model.AccountsInfoDTO
import com.sanjeet.simpletrancsaction.data.model.TransactionsDTO
import com.sanjeet.simpletrancsaction.data.remote.TransactionApi
import com.sanjeet.simpletrancsaction.domain.repository.HomeRepository

class HomeRepoImpl(private val transactionApi: TransactionApi) : HomeRepository {

    override suspend fun getAccounts(): List<AccountsInfoDTO> {
        return transactionApi.getAccounts()
    }

    override suspend fun getTransactions(accountId: String): List<TransactionsDTO> {
        return transactionApi.getTransactions(accountId)
    }


}