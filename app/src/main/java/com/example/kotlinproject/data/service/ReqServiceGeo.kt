package com.example.kotlinproject.data.service

import com.example.kotlinproject.app_base.BaseREQ
import com.example.kotlinproject.data.api.API_GET_GEO

class ReqServiceGeo {
    companion object {
        var instance: ReqServiceGeo? = null

        fun get(): ReqServiceGeo {
            synchronized(this) {
                if (instance == null) {
                    instance = ReqServiceGeo()
                }
                return instance!!
            }
        }
    }

    var req: API_GET_GEO? = null

    init {
        req = BaseREQ.get().request!!.create(API_GET_GEO::class.java)
    }

    fun get(): API_GET_GEO {
        return req!!
    }
}