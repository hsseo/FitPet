package com.example.kotlinproject.ui.page.view.weather_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinproject.data.domain.weather.DtWeather

class WeatherDetailViewModelFactory(weathers: DtWeather) : ViewModelProvider.Factory {

    var weathers : DtWeather?= weathers

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WeatherDetailViewModel::class.java)) {
            return WeatherDetailViewModel(weathers!!) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}