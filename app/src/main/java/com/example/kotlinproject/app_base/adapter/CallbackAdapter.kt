package com.example.kotlinproject.app_base.adapter

import com.example.kotlinproject.app_base.BaseData


interface CallbackAdapter {
    fun listRefresh()
    fun listMore(page : Int)
    fun listClick(type : Int, data : BaseData)
}