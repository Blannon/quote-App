package com.blannon_network.quoteapp.data

import com.blannon_network.quoteapp.domain.model.Quote
import com.blannon_network.quoteapp.utils.Constant
import retrofit2.Response
import retrofit2.http.GET

interface QuoteApi {
    @GET(Constant.END_POINT)
    suspend fun getQuote(): Response<Quote>
}