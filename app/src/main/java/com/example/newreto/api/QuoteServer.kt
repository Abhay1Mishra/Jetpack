package com.example.newreto.api

import com.example.newreto.model.QuotesList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteServer {
    @GET("/quotes")
    suspend fun getQuotes(@Query("page") page:Int):Response<QuotesList>
}