package com.example.kotlinproject.data.repository.weather

import com.example.kotlinproject.app_base.CallbackAPI

interface RepositoryWeather {
    fun getWeather(cityName : String, callback : CallbackAPI)
    fun getGeo(cityName:String, callback : CallbackAPI)
}