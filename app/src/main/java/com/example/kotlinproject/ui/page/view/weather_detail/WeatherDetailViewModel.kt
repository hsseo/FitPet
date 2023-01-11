package com.example.kotlinproject.ui.page.view.weather_detail

import androidx.lifecycle.MutableLiveData
import com.example.kotlinproject.app_base.BaseViewModel
import com.example.kotlinproject.data.domain.weather.DtWeather

class WeatherDetailViewModel(var weathers : DtWeather) : BaseViewModel() {
    val weathersLiveData : MutableLiveData<DtWeather> = MutableLiveData()
    
    fun loadWeathers() {
        weathersLiveData.postValue(weathers)
    }
}