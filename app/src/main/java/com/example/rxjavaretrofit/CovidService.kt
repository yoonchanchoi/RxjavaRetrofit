package com.example.mvvmretrofit

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CovidService{
    @GET("/korea/country/new/")
    fun getRepos(@Query("serviceKey") serviceKey: String): Single<StateVO>
}