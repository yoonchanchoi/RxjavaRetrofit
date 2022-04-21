package com.example.mvvmretrofit.repository

import com.example.mvvmretrofit.network.CovidRetrofit

class CovidRepository {

    fun getCovidInfo(serviceKey: String) =
        CovidRetrofit.covidApiService.getDocument(serviceKey)


}