package com.example.kotlinproject.utils

import com.example.kotlinproject.app_base.Apps
import com.example.kotlinproject.utils.listener.ListenerDelay
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class Utils {
    companion object {
        var instance : Utils ?= null
        fun get() : Utils {
            synchronized(this) {
                if(instance == null) {
                    instance = Utils()
                }
                return instance!!
            }
        }
    }

    fun string(resId:Int) : String {
        return Apps.context().getString(resId) ?: ""
    }

    fun parseBool(value: String?): Boolean {
        return try {
            java.lang.Boolean.parseBoolean(value)
        } catch (e: Exception) {
            false
        }
    }

    fun parseInt(value: String): Int {
        var value = value
        return try {
            value = value.replace(",", "")
            value.toInt()
        } catch (e: Exception) {
            -1
        }
    }

    fun parseLong(value: String): Long {
        var value = value
        return try {
            value = value.replace(",", "")
            value.toLong()
        } catch (e: Exception) {
            -1
        }
    }

    fun parseDouble(value: String): Double {
        return try {
            value.toDouble()
        } catch (e: Exception) {
            (-1).toDouble()
        }
    }

    fun parseFloat(value: String): Float {
        return try {
            value.toFloat()
        } catch (e: Exception) {
            (-1).toFloat()
        }
    }

    fun delay(subscription: CompositeDisposable, delayTime: Long, listener: ListenerDelay) {
        subscription.add(io.reactivex.Observable.empty<Any>()
            .delay(delayTime, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({ }, { }) {
                listener.done()
            })
    }
}