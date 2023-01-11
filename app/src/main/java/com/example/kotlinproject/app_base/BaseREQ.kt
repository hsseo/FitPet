package com.example.kotlinproject.app_base

import com.example.kotlinproject.constants.ConstAPI
import com.example.kotlinproject.constants.ConstAPP
import com.example.kotlinproject.utils.UtilsLog
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class BaseREQ {
    companion object {
        var TAG = BaseREQ::class.java.name
        var instance: BaseREQ? = null

        fun get(): BaseREQ {
            synchronized(this) {
                if (instance == null) {
                    instance = BaseREQ()
                }
                return instance!!
            }
        }
    }

    var request : Retrofit ?= null

    init {
        val logging = HttpLoggingInterceptor { message -> UtilsLog.e(TAG, message) }
        if (ConstAPP.IS_DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.NONE
        }
        val client = OkHttpClient().newBuilder()
            .readTimeout(1000, TimeUnit.MILLISECONDS)
            .writeTimeout(1000, TimeUnit.MILLISECONDS)
            .addNetworkInterceptor(logging).build()
        request = Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ConstAPI.API_WEATHER_URL).build()
    }
}