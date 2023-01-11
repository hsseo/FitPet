package com.example.kotlinproject.data.repository.weather

import com.example.kotlinproject.app_base.CallbackAPI
import com.example.kotlinproject.constants.ConstAPI
import com.example.kotlinproject.data.api.API_GET_GEO
import com.example.kotlinproject.data.api.API_GET_WEATHER
import com.example.kotlinproject.data.model.ResponseGeo
import com.example.kotlinproject.data.model.ResponseWeather
import com.example.kotlinproject.data.service.ReqServiceGeo
import com.example.kotlinproject.data.service.ReqServiceWeather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DataSourceWeatherRemote : DataSourceWeather {
    companion object {
        var instnace: DataSourceWeatherRemote? = null
        fun get(): DataSourceWeatherRemote {
            synchronized(this) {
                if (instnace == null) {
                    instnace = DataSourceWeatherRemote()
                }
                return instnace!!
            }
        }
    }

    var apiGetWeather: API_GET_WEATHER ? = null
    var apiGetGeo : API_GET_GEO ?= null

    init {
        this.apiGetWeather = ReqServiceWeather.get().get()
        this.apiGetGeo = ReqServiceGeo.get().get()
    }

    override fun getWeahter(cityName : String, callBack: CallbackAPI) {
        apiGetWeather!!.getWeather(
            ConstAPI.API_KEY,
            cityName
        )!!
            .enqueue(object : Callback<ResponseWeather?> {
                override fun onResponse(
                    call: Call<ResponseWeather?>,
                    response: Response<ResponseWeather?>
                ) {
                    if(response.isSuccessful) {
                        callBack.success(response.body()!!)
                    } else {
                        callBack.error(response.code(), response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<ResponseWeather?>, t: Throwable) {
                    callBack.error(t.hashCode(), t.localizedMessage)
                }
            })
    }

    override fun getGeo(cityName:String, callBack: CallbackAPI) {
        apiGetGeo!!.getGeo(
            ConstAPI.API_KEY,
            cityName
        )!!
            .enqueue(object : Callback<ArrayList<ResponseGeo>?> {
                override fun onResponse(
                    call: Call<ArrayList<ResponseGeo>?>,
                    response: Response<ArrayList<ResponseGeo>?>
                ) {
                    if(response.isSuccessful) {
                        callBack.success(response.body()!!.get(0))
                    } else {
                        callBack.error(response.code(), response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<ArrayList<ResponseGeo>?>, t: Throwable) {
                    callBack.error(t.hashCode(), t.localizedMessage)
                }
            })
    }
}