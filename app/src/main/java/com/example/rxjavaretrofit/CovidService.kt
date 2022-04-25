package com.example.rxjavaretrofit

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CovidService{
    @GET("/korea/country/new/")
    fun getDocument(@Query("serviceKey") serviceKey: String): Observable<StateVO>
}