package com.example.kotlinproject.utils

import android.content.Context
import androidx.fragment.app.Fragment
import pub.devrel.easypermissions.EasyPermissions

class UtilsPermission {
    companion object {
        var instance: UtilsPermission? = null

        fun get(): UtilsPermission {
            synchronized(this) {
                if (instance == null) {
                    instance = UtilsPermission()
                }
                return instance!!
            }
        }
    }

    fun req(
        conetxt: Context,
        reqIdx: Int,
        vararg permissions: String,
        fragment: Fragment
    ): Boolean {
        if (EasyPermissions.hasPermissions(conetxt, permissions.toString())) {
            return true;
        }
        EasyPermissions.requestPermissions(
            fragment,
            "권한 승인 후 다시 시도해주세요.",
            reqIdx,
            permissions.toString()
        )
        return false;
    }

    fun req(
        conetxt: Context,
        reqIdx: Int,
        vararg permissions: String,
        fragment: Fragment,
        description : String
    ): Boolean {
        if (EasyPermissions.hasPermissions(conetxt, permissions.toString())) {
            return true;
        }
        EasyPermissions.requestPermissions(
            fragment,
            description,
            reqIdx,
            permissions.toString()
        )
        return false;
    }
}