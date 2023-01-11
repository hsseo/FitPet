package com.example.kotlinproject.utils

import com.example.kotlinproject.BuildConfig
import com.example.kotlinproject.global.GlobalDatas
import java.util.*

class UtilsAPI {
    companion object {
        fun version() : Int {
            return BuildConfig.VERSION_CODE
        }
        fun lang() : String {
            return Locale.getDefault().isO3Language
        }
    }
}