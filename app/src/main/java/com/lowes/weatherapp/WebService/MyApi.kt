package com.lowes.weatherapp.WebService

import com.google.gson.GsonBuilder
import com.lowes.weatherapp.WebService.Objects.Example
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface MyApi {
        companion object {
            val BASE_URL:String="https://api.openweathermap.org/data/2.5/"
            var longerTimeoutClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()
            var gson = GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create()
            var retrofitApiInstance = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(longerTimeoutClient)
                .build()
            fun create(): MyApi{
                return retrofitApiInstance.create(MyApi::class.java)
            }
        }


        @GET("forecast?")
        fun getCityWeatherData(@Query("q") q:String,
                               @Query("appid")appid:String=
        "65d00499677e59496ca2f318eb68c049",@Query("units")units:String="imperial"): Call<Example>


}