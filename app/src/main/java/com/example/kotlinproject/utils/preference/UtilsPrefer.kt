package com.example.kotlinproject.utils.preference

import com.example.kotlinproject.constants.ConstPrefer

class UtilsPrefer {
    companion object {
        var instance : UtilsPrefer?= null

        fun get() : UtilsPrefer {
            synchronized(this) {
                if (instance == null) {
                    instance = UtilsPrefer()
                }
                return instance!!
            }
        }
    }

    fun test() : String = PrefBase.get().getS(ConstPrefer.PREFER_TEST)
    fun test(value : String) = PrefBase.get().put(ConstPrefer.PREFER_TEST, value)
}