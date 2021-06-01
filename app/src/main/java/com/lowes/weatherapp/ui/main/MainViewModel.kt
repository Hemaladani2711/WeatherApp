package com.lowes.weatherapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lowes.weatherapp.data.RepositoryImpl
import com.lowes.weatherapp.WebService.Objects.Example
import com.lowes.weatherapp.data.Repository

class MainViewModel(val repository: Repository= RepositoryImpl()):ViewModel() {
    fun getWeatherData(city:String):LiveData<Example?>{
        return repository.getCityWeatherData(city)
    }

}