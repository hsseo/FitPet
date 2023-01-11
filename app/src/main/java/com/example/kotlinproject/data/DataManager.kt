package com.example.kotlinproject.data

import com.example.kotlinproject.data.repository.weather.DataSourceWeatherRemote
import com.example.kotlinproject.data.repository.weather.RepositoryWeather
import com.example.kotlinproject.data.repository.weather.RepositoryWeatherImpl

class DataManager {
    companion object {
        var instance : DataManager ?= null
        fun get() : DataManager {
            synchronized(this) {
                if (instance == null) {
                    instance = DataManager()
                }
                return instance!!
            }
        }
    }

    fun getWeatherRepository() : RepositoryWeather {
        return RepositoryWeatherImpl.get(DataSourceWeatherRemote.get())
    }
}