package com.example.planzee.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitAPI {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.0.58:4500/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiInterface: APIService by lazy {
        retrofit.create(APIService::class.java)
    }
}