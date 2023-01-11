package com.example.kotlinproject.data.api

import com.example.kotlinproject.data.model.ResponseWeather
import retrofit2.Call
import retrofit2.http.*

interface API_GET_WEATHER {
    @GET("/data/2.5/forecast")
    fun getWeather(
        @Query("appid") appid: String,
        @Query("q") cityName: String,
        @Query("cnt") cnt: Int = 48,
        @Query("units") units : String = "metric"
    ): Call<ResponseWeather?>?
}