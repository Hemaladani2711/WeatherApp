package com.lowes.weatherapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lowes.weatherapp.WebService.MyApi
import com.lowes.weatherapp.data.RemoteRepository
import com.lowes.weatherapp.data.RemoteRepositoryImpl
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RemoteRepositoryTest {
    private val City = "Edison"
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var remoteRepository: RemoteRepository

    @Before
    fun setUp() {
        remoteRepository = RemoteRepositoryImpl()
    }
    @Test
    fun verifyApiResponseIsNotNull(){
        val callWithCity = MyApi.create().getCityWeatherData(City)
        val callResponse=callWithCity.execute()
        Assert.assertEquals(callResponse.code(),200)
        val weatherData =  remoteRepository.getCityWeatherData(City)
        Assert.assertEquals(City,weatherData.getOrAwaitValue()?.city?.name)
    }
    @Test
    fun verifyResponseForEmptyCityShouldBeNull(){
        val callWithOutCity = MyApi.create().getCityWeatherData("")
        val callResponseEmpty=callWithOutCity.execute()
        Assert.assertEquals(400,callResponseEmpty.code())
        val weatherData =  remoteRepository.getCityWeatherData("")
        Assert.assertEquals(null,weatherData.getOrAwaitValue())
    }

}