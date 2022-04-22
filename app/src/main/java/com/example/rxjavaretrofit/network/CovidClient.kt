package com.example.rxjavaretrofit.network

import com.example.mvvmretrofit.CovidApi
import com.example.mvvmretrofit.CovidService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class CovidClient {

    companion object {
        fun getApi(): CovidService = Retrofit.Builder()
            .baseUrl(CovidApi.DOMAIN)
            .client(OkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CovidService::class.java)
    }



    private fun provideOkHttpClient(interceptor: HttpCovidIntrcepter): OkHttpClient
        val b = OkHttpClient.Builder
        //  이 클라이언트를 통해 오고 가는 네트워크 요청/응답을 로그로 표시하도록 합니다.
        b.addInterceptor(interceptor)
        return b.build()
    }


}