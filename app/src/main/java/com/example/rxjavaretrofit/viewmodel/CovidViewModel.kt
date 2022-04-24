package com.example.rxjavaretrofit.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rxjavaretrofit.CovidApi
import com.example.rxjavaretrofit.CovidVO
import com.example.rxjavaretrofit.StateVO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CovidViewModel :ViewModel(){

    private val _liveCovidVo: MutableLiveData<ArrayList<CovidVO?>> = MutableLiveData()
    private val _liveStateVo: MutableLiveData<StateVO> = MutableLiveData()
    private val _liveToast: MutableLiveData<String> = MutableLiveData()

    val liveCovidVo: LiveData<ArrayList<CovidVO?>>
        get() = _liveCovidVo
    val liveStateVo: LiveData<StateVO>
        get() = _liveStateVo
    val liveToast: LiveData<String>
        get() = _liveToast

    fun getAll(key: String){
        CovidClient.getApi().getRepos(CovidApi.TOKEN)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            },{

            })



        (object : Callback<StateVO>{
            override fun onResponse(call: Call<StateVO>, response: Response<StateVO>) {
                    val regionList = arrayListOf(
                        response.body()?.korea,
                        response.body()?.seoul,
                        response.body()?.busan,
                        response.body()?.incheon,
                        response.body()?.gwangju,
                        response.body()?.jeonbuk,
                        response.body()?.chungbuk,
                        response.body()?.jeonnam,
                        response.body()?.gyeongbuk,
                        response.body()?.daegu,
                        response.body()?.ulsan,
                        response.body()?.daejeon,
                        response.body()?.sejong,
                        response.body()?.chungnam,
                        response.body()?.gyeonggi,
                        response.body()?.gyeongnam,
                        response.body()?.gangwon,
                        response.body()?.jeju,
                        response.body()?.quarantine
                    )
                Log.d("성공성공!", regionList.toString())
                    _liveCovidVo.postValue(regionList)

            }

            override fun onFailure(call: Call<StateVO>, t: Throwable) {
                Log.d("실패실패..", "${t.localizedMessage.toString()}")
                    Log.d("실패실패..", "${t.message.toString()}")
                _liveToast.postValue(t.localizedMessage)
            }

        })


    }

}