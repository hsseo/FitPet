package com.example.kotlinproject.app_base

import android.app.Application
import android.content.Context
import android.os.Handler
import androidx.multidex.MultiDex

class Apps : Application() {
    @Volatile
    var handler: Handler? = null

    override fun onCreate() {
        super.onCreate()
        handler = Handler(ContextApps.get()!!.mainLooper)
        initialize()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
        base?.let { ContextApps.init(it) }
    }

    private fun initialize() {
    }

    companion object {
        fun context(): Context {
            return ContextApps.get()!!
        }
    }
}