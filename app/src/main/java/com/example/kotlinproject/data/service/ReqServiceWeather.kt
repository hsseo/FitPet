package com.example.kotlinproject.data.service

import com.example.kotlinproject.app_base.BaseREQ
import com.example.kotlinproject.data.api.API_GET_WEATHER

class ReqServiceWeather {
    companion object {
        var instance: ReqServiceWeather? = null

        fun get(): ReqServiceWeather {
            synchronized(this) {
                if (instance == null) {
                    instance = ReqServiceWeather()
                }
                return instance!!
            }
        }
    }

    var req: API_GET_WEATHER? = null

    init {
        req = BaseREQ.get().request!!.create(API_GET_WEATHER::class.java)
    }

    fun get(): API_GET_WEATHER {
        return req!!
    }

}