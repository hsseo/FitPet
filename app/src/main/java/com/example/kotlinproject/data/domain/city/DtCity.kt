package com.example.kotlinproject.data.domain.city

import com.example.kotlinproject.R
import com.example.kotlinproject.app_base.Apps
import com.example.kotlinproject.app_base.BaseData

class DtCity(var name: String = "") : BaseData() {
    var id = 0.0
    var timezone = 0
    var sunrise = 0.0
    var sunset = 0.0

    fun cityName() : String {
        return Apps.context().getString(R.string.format_for_weather, name)
    }
}