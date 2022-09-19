package com.sanjeet.simpletrancsaction.view.Home

import com.sanjeet.simpletrancsaction.domain.model.TransactionDetails

data class TransactionState(
    val data: List<TransactionDetails>? = null,
    val error: String = "",
    val isLoading: Boolean = false
)