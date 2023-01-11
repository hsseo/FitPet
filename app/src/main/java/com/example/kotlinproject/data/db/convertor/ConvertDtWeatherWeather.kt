package com.example.kotlinproject.data.db.convertor

import androidx.room.TypeConverter
import com.example.kotlinproject.data.domain.weather.DtWeather_Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConvertDtWeatherWeather {
    @TypeConverter
    fun fromList(value : List<DtWeather_Weather>?) : String? {
        return Gson().toJson(value)
    }
    @TypeConverter
    fun fromJson(value : String?) : List<DtWeather_Weather>? {
        return Gson().fromJson(
            value,
            object : TypeToken<List<DtWeather_Weather>>() {}.type
        )
    }
}