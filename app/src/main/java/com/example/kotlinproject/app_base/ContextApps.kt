package com.example.kotlinproject.app_base

import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager

class ContextApps(base: Context?) : ContextWrapper(base) {
    companion object {
        private var instance: ContextApps? = null

        fun init(context: Context) {
            synchronized(this) {
                if (instance == null) {
                    try {
                        instance = ContextApps(
                            context.createPackageContext(
                                context.packageName, Context.CONTEXT_INCLUDE_CODE
                            )
                        )
                    } catch (e: PackageManager.NameNotFoundException) {
                        throw RuntimeException(e)
                    }
                }
            }
        }

        fun get(): ContextApps? {
            return instance
        }
    }
}