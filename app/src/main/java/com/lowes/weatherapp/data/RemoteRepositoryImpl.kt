package com.lowes.weatherapp.data

import androidx.lifecycle.MutableLiveData
import com.lowes.weatherapp.WebService.MyApi
import com.lowes.weatherapp.WebService.Objects.Example
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepositoryImpl:RemoteRepository {
    override fun getCityWeatherData(cityName:String): MutableLiveData<Example?> {
        val data = MutableLiveData<Example?>()
        val apiService: MyApi = MyApi.create()
        val mCallGetData: Call<Example> = apiService.getCityWeatherData(cityName)
        mCallGetData.enqueue(object : Callback<Example?> {
            override fun onFailure(call: Call<Example?>, t: Throwable) {
                data.value=null
            }
            override fun onResponse(call: Call<Example?>, response: Response<Example?>) {
                val city: Example?=response.body()
                data.value=city
            }
        } )
        return data

    }
}