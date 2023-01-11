package com.example.kotlinproject.utils.preference

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.fragment.app.Fragment
import com.example.kotlinproject.app_base.Apps
import com.example.kotlinproject.utils.listener.ListenerDelay
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class PrefBase {
    companion object {
        var instance : PrefBase ?= null
        fun get() : PrefBase {
            synchronized(this) {
                if (instance == null) {
                    instance = PrefBase()

                }
                return instance!!
            }
        }
    }


    var pref : SharedPreferences ?= null

    init {
        pref = Apps.context()!!.getSharedPreferences(Apps.context()!!.packageName, Context.MODE_PRIVATE)
    }

    fun put(key : String, value : Boolean) {
        pref!!.edit().putBoolean(key, value).apply()
    }
    fun put(key : String, value : Int) {
        pref!!.edit().putInt(key, value).apply()
    }
    fun put(key : String, value : String) {
        pref!!.edit().putString(key, value).apply()
    }
    fun getB(key : String) : Boolean {
        return pref!!.getBoolean(key, false)
    }
    fun getI(key : String) : Int {
        return pref!!.getInt(key, -1)
    }
    fun getS(key : String) : String {
        return pref!!.getString(key, "").toString()
    }


}