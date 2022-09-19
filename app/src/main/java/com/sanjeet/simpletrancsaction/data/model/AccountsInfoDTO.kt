package com.sanjeet.simpletrancsaction.data.model

import com.sanjeet.simpletrancsaction.domain.model.AccountDetails

data class AccountsInfoDTO(
    val id: String,
    val name: String,
    val balance: Double
)

fun AccountsInfoDTO.toDomainAccountDetails(): AccountDetails {
    return AccountDetails(
        id = this.id,
        name = this.name,
        balance = this.balance
    )
}