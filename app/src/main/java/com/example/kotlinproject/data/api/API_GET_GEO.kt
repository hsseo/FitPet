package com.example.kotlinproject.data.api

import com.example.kotlinproject.data.model.ResponseGeo
import retrofit2.Call
import retrofit2.http.*

interface API_GET_GEO {
    @GET("/geo/1.0/direct")
    fun getGeo(
        @Query("appid") appid: String,
        @Query("q") q: String?,
        @Query("limit") limit: Int = 1
    ): Call<ArrayList<ResponseGeo>?>?
}