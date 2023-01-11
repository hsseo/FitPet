package com.example.kotlinproject.data.model

import com.example.kotlinproject.app_base.BaseData

class ResponseGeo : BaseData() {
    var name = ""
    var lat : Float = 0.0F
    var lon : Float = 0.0F
    var country = ""
    var state = ""
}