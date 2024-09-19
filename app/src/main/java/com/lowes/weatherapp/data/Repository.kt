package com.lowes.weatherapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lowes.weatherapp.WebService.Objects.Example

interface Repository {
    fun getCityWeatherData(cityName:String): MutableLiveData<Example?>
}