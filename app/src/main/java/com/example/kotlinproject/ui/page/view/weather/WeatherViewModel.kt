package com.example.kotlinproject.ui.page.view.weather

import androidx.lifecycle.MutableLiveData
import com.example.kotlinproject.app_base.BaseData
import com.example.kotlinproject.app_base.BaseViewModel
import com.example.kotlinproject.constants.ConstAPP
import com.example.kotlinproject.data.model.ResponseGeo
import com.example.kotlinproject.data.model.ResponseWeather
import com.example.kotlinproject.app_base.CallbackAPI
import com.example.kotlinproject.data.repository.weather.RepositoryWeather

class WeatherViewModel(var wordViewRepository : RepositoryWeather) : BaseViewModel() {
    val apiWeather : MutableLiveData<Boolean> = MutableLiveData()
    val weatherLiveData: MutableLiveData<List<ResponseWeather>> = MutableLiveData()
    val weatherErrorLiveData: MutableLiveData<String> = MutableLiveData()
    val geoLiveData: MutableLiveData<List<ResponseGeo>> = MutableLiveData()

    val weatherDatas : ArrayList<ResponseWeather> = ArrayList()

    private fun requestWeather(cityName : String) {
        wordViewRepository.getWeather(cityName, object : CallbackAPI {
            override fun success(datas: BaseData) {
                weatherDatas.add(datas as ResponseWeather)
                if(weatherDatas.size == ConstAPP.CITY_NAMES.size) {
                    hideLoading()
                    weatherLiveData.postValue(weatherDatas)
                } else {
                    requestWeather(ConstAPP.CITY_NAMES[weatherDatas.size])
                }
            }

            override fun error(errorCode: Int, errorMessage: String) {
                hideLoading()
                weatherErrorLiveData.postValue(errorMessage)
            }
        })
    }

    fun apiWeather() {
        showLoading()
        weatherDatas.clear()
        requestWeather(ConstAPP.CITY_NAMES[0])
    }


}