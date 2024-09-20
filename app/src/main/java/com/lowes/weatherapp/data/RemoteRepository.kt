package com.lowes.weatherapp.data

import androidx.lifecycle.MutableLiveData
import com.lowes.weatherapp.WebService.Objects.Example

interface RemoteRepository {
    fun getCityWeatherData(cityName:String): MutableLiveData<Example?>
}