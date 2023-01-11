package com.example.kotlinproject.data.repository.weather

import com.example.kotlinproject.app_base.CallbackAPI

interface DataSourceWeather {
    fun getWeahter(cityName: String, callBack: CallbackAPI)
    fun getGeo(cityName:String, callBack: CallbackAPI)
}