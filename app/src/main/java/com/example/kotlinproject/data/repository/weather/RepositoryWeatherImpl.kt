package com.example.kotlinproject.data.repository.weather

import com.example.kotlinproject.app_base.CallbackAPI

class RepositoryWeatherImpl(var wordViewDataSource: DataSourceWeatherRemote) : RepositoryWeather {
    companion object {
        var instance: RepositoryWeatherImpl? = null

        fun get(wordViewDataSource: DataSourceWeatherRemote): RepositoryWeatherImpl {
            synchronized(this) {
                if (instance == null) {
                    instance = RepositoryWeatherImpl(wordViewDataSource)
                }
                return instance!!
            }
        }
    }

    override fun getWeather(cityName: String, callback: CallbackAPI) {
        wordViewDataSource!!.getWeahter(cityName, callback)
    }

    override fun getGeo(cityName: String, callback: CallbackAPI) {
        wordViewDataSource!!.getGeo(cityName, callback)
    }
}