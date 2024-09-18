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

//TODO: add more tests compare with mock data
@RunWith(JUnit4::class)
class GetPersonalDetailsApiTest {
    private val City = "Edison"
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var repository: Repository
    private lateinit var mockedResponse: String
    lateinit var callWithCity: Call<Example>
    lateinit var callWithOutCity: Call<Example>
    private lateinit var callResponseEmpty:Response<Example>
    private lateinit var callResponse:Response<Example>

    @Before
    fun setUp() {
        callWithCity = MyApi.create().getCityWeatherData(City)
        callWithOutCity = MyApi.create().getCityWeatherData("")
        repository = RepositoryImpl()
        mockedResponse = MockResponseFileReader.readFileWithNewLineFromResources("success.json")
        callResponse=callWithCity.execute()
        callResponseEmpty = callWithOutCity.execute()

    }
    @Test
    fun testApiResponseIsNotNull() {
        Assert.assertEquals(callResponse.code(),200)
    }
    @Test
    fun testResponseContainsTheSameCity(){
        Assert.assertEquals(City,callResponse.body()?.city?.name)
    }
    @Test
    fun testResponse400(){
        Assert.assertEquals(callResponseEmpty.code(),400)
    }
}