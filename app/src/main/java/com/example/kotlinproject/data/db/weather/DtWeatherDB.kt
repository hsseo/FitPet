package com.example.kotlinproject.data.db.weather

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.kotlinproject.app_base.BaseData
import com.example.kotlinproject.data.db.convertor.ConvertDtWeatherWeather
import com.example.kotlinproject.data.domain.weather.*

@Entity(tableName = "weather_db")
open class DtWeatherDB : BaseData() {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0

    var dt : Long = 0
    var visibility = 0.0
    var pop = 0.0
    var dt_txt = ""

    @Embedded var sys : DtWeather_Sys = DtWeather_Sys()
    @Embedded var main : DtWeather_Main = DtWeather_Main()
    @Embedded var clouds : DtWeather_Clouds = DtWeather_Clouds()
    @Embedded var wind : DtWeather_Wind = DtWeather_Wind()

    @TypeConverters(ConvertDtWeatherWeather::class)
    var weather : List<DtWeather_Weather> = ArrayList()
}