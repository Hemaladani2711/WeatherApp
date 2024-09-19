package com.lowes.weatherapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lowes.weatherapp.WebService.MyApi
import com.lowes.weatherapp.WebService.Objects.Example
import com.lowes.weatherapp.data.Repository
import com.lowes.weatherapp.data.RepositoryImpl
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Call
import retrofit2.Response

@RunWith(JUnit4::class)
class GetPersonalDetailsApiTest {
    private val City = "Edison"
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        repository = RepositoryImpl()
    }
    @Test
    fun testApiResponseIsNotNull(){
        val callWithCity = MyApi.create().getCityWeatherData(City)
        val callResponse=callWithCity.execute()
        Assert.assertEquals(callResponse.code(),200)
        val weatherData =  repository.getCityWeatherData(City)
        Assert.assertEquals(City,weatherData.getOrAwaitValue()?.city?.name)
    }
    @Test
    fun testResponseForEmptyCityShouldBeNull(){
        val callWithOutCity = MyApi.create().getCityWeatherData("")
        val callResponseEmpty=callWithOutCity.execute()
        Assert.assertEquals(400,callResponseEmpty.code())
        val weatherData =  repository.getCityWeatherData("")
        Assert.assertEquals(null,weatherData.getOrAwaitValue())
    }


}