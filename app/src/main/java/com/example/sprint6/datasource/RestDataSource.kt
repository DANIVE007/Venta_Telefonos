package com.example.sprint6.datasource

import com.example.sprint6.model.UserProduct
import com.example.sprint6.util.Constants.Companion.ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RestDataSource {

    @GET(ENDPOINT)
    suspend fun getProductos(): List<UserProduct>

    @GET("${ENDPOINT}/{id}")
    suspend fun getProductosById(@Path(value="id") id: Int): Response<UserProduct>

    }