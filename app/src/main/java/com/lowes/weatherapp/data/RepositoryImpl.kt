package com.lowes.weatherapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lowes.weatherapp.WebService.MyApi
import com.lowes.weatherapp.WebService.Objects.Example
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryImpl:Repository {
    override fun getCityWeatherData(cityName:String): LiveData<Example?> {
        val data = MutableLiveData<Example?>()
        var apiService: MyApi = MyApi.create()
        var mCallGetData: Call<Example> = apiService.getCityWeatherData(cityName)
        mCallGetData.enqueue(object : Callback<Example?> {
            override fun onFailure(call: Call<Example?>, t: Throwable) {
                Log.d("Error",t.message.toString())
                data.value=null
            }

            override fun onResponse(call: Call<Example?>, response: Response<Example?>) {
                Log.d("Success","")
                val city: Example?=response.body()
                data.value=city
            }
        } )
        return data

    }
}