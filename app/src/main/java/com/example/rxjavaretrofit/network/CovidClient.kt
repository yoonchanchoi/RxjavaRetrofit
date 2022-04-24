package com.example.rxjavaretrofit.network

import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import com.example.mvvmretrofit.CovidApi
import com.example.mvvmretrofit.CovidService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object CovidClient {
    private var instance: Retrofit? = null
    private const val CONNECT_TIMEOUT_SEC = 2000L

    fun getApi() : Retrofit{
        if(instance == null){

            //코로나인터셉터 세팅
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            //OKHttpClient에 로깅인터셉터 등록
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(CONNECT_TIMEOUT_SEC, TimeUnit.SECONDS)
                .build()


            //레트로핏에 인터셉터 올리기
            instance = Retrofit.Builder()
                .baseUrl(CovidApi.TOKEN)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client) //여기 부분에서 인터셉터를 가진 client를 올리는 것을 볼 수 있습니다.
                .build()

        }
        return instance!!
    }
}