package com.lowes.weatherapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.lowes.weatherapp.WebService.Objects.Example
import com.lowes.weatherapp.data.Repository
import com.lowes.weatherapp.data.RepositoryImpl
import com.lowes.weatherapp.ui.main.MainViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainViewModelTest {
    private val City = "Edison"
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: Repository
    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp(){
        repository = RepositoryImpl()
        mainViewModel = MainViewModel(repository)
    }

    @Test
    fun verifyGetWeatherDataCityNameIsTheSame(){
        val weatherData = mainViewModel.getWeatherData(City)
        Assert.assertEquals(City,weatherData.getOrAwaitValue()?.city?.name)
    }

    @Test
    fun verifyGetWeatherDataCityNameIsNullIfPassedEmpty(){
        val weatherData = mainViewModel.getWeatherData("")
        Assert.assertEquals(null,weatherData.getOrAwaitValue())
    }

}