package com.sanjeet.simpletrancsaction.data.model

import com.sanjeet.simpletrancsaction.domain.model.TransactionDetails

data class TransactionsDTO(
    val id: String,
    val title: String,
    val balance: Double
)

fun TransactionsDTO.toDomainTransactionDetails(): TransactionDetails {
    return TransactionDetails(
        id = this.id,
        title = this.title,
        balance = this.balance
    )
}