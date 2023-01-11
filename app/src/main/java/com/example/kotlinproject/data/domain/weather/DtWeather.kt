package com.example.kotlinproject.data.domain.weather

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.View
import com.example.kotlinproject.R
import com.example.kotlinproject.app_base.Apps
import com.example.kotlinproject.data.db.weather.DtWeatherDB
import com.example.kotlinproject.data.domain.city.DtCity
import com.example.kotlinproject.utils.UtilsDate


class DtWeather : DtWeatherDB() {
    var isFirst : Boolean = false
    var city : DtCity?= null
    val weatherTime : ArrayList<DtWeather> = ArrayList()

    fun getWeather() : DtWeather_Weather {
        try {
            return weather.get(0)
        } catch (ignore : Exception) {
            return DtWeather_Weather()
        }
    }

    fun getDate():String {
        return dt_txt.split(" ")[0]
    }

    fun getDateO():String {
        return UtilsDate.getFormattedDate(dt_txt)
    }

    fun getDescription():String {
        try {
            return weather.get(0).main
        } catch (ignore : Exception) {
            return ""
        }
    }

    fun getMaxTmp():String {
        return Apps.context().getString(R.string.format_max_tmp, main.temp_max.toString())
    }

    fun getMinTmp():String {
        return Apps.context().getString(R.string.format_min_tmp, main.temp_max.toString())
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun getResIcon() : Drawable {
        val id = Apps.context().resources.getIdentifier("s_" + getWeather().icon, "drawable", Apps.context().packageName)
        return Apps.context().resources.getDrawable(id)
    }

    fun getIsFirst() : Int {
        return if(isFirst) View.VISIBLE else View.GONE
    }

    @SuppressLint("StringFormatMatches")
    fun cityNameWithDate() : String {
        return Apps.context().getString(R.string.format_for_weather_with_date, city?.name, getDate())
    }
}