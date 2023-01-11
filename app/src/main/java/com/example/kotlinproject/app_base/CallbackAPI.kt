package com.example.kotlinproject.app_base


interface CallbackAPI {
    fun success(datas : BaseData)
    fun error(errorCode : Int, errorMessage : String)
}