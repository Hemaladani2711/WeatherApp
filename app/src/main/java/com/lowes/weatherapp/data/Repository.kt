package com.lowes.weatherapp.data

import androidx.lifecycle.LiveData
import com.lowes.weatherapp.WebService.Objects.Example

interface Repository {
    fun getCityWeatherData(cityName:String):LiveData<Example?>
}