package com.example.kotlinproject.data.domain.weather

import androidx.room.ColumnInfo
import com.example.kotlinproject.app_base.BaseData

class DtWeather_Weather : BaseData() {
    var id = 0
    var main = ""
    var description = ""
    var icon = ""
}