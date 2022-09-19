package com.sanjeet.simpletrancsaction.view.Home

import com.sanjeet.simpletrancsaction.domain.model.AccountDetails

data class HomeState(
    val data: List<AccountDetails>? = null,
    val error: String = "",
    val isLoading: Boolean = false
)