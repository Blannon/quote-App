package com.blannon_network.quoteapp.utils

import com.blannon_network.quoteapp.domain.model.Quote
import retrofit2.Response

sealed class ApiState {
    data class Success(val data: Response<Quote>): ApiState()
    data class Failure(val error: Throwable) : ApiState()
    data object Loading: ApiState()
    data object Idle : ApiState()
}