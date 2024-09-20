package com.lowes.weatherapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lowes.weatherapp.data.RemoteRepositoryImpl
import com.lowes.weatherapp.WebService.Objects.Example
import com.lowes.weatherapp.data.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val remoteRepositoryImpl: RemoteRepositoryImpl):ViewModel() {
    fun getWeatherData(city:String):MutableLiveData<Example?>{
        return remoteRepositoryImpl.getCityWeatherData(city)
    }

}