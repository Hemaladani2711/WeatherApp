package com.lowes.weatherapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lowes.weatherapp.data.RemoteRepository
import com.lowes.weatherapp.data.RemoteRepositoryImpl
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

    private lateinit var remoteRepository: RemoteRepository
    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp(){
        remoteRepository = RemoteRepositoryImpl()
        mainViewModel = MainViewModel(remoteRepository)
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