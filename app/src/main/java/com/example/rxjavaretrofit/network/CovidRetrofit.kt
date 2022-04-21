package com.example.mvvmretrofit.network

import com.example.mvvmretrofit.CovidApi
import com.example.mvvmretrofit.CovidService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CovidRetrofit {

    private val retrofit = Retrofit.Builder()
        .baseUrl(CovidApi.DOMAIN)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val covidApiService: CovidService by lazy {
        retrofit.create(CovidService::class.java)
    }

}