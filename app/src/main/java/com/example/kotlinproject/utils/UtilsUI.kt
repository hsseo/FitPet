package com.example.kotlinproject.utils

import android.content.Context
import android.graphics.Paint
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.kotlinproject.app_base.Apps
import kotlin.math.ceil

class UtilsUI {
    companion object {
        var instance : UtilsUI ?= null
        fun get() : UtilsUI {
            synchronized(this) {
                if(instance == null) {
                    instance = UtilsUI()
                }
                return instance!!
            }
        }
    }
    fun unbindViewGroup(vGroup : ViewGroup) {
        try {
            var viewCnt = vGroup.childCount
            for(i in 0 until viewCnt - 1) {
                var view : View = vGroup.getChildAt(i)
                if(view is ViewGroup) {
                    unbindViewGroup(view)
                }
            }
        } catch (ignore : Exception) {}
    }

    fun dp(value:Float) : Float {
        val density : Float? = Apps.context()?.resources?.displayMetrics?.density
        if (density != null) {
            return density * value
        }
        return 0.0F
    }

    fun keyPad(isShow : Boolean, view : View) {
        view.requestFocus()
        var inputManager : InputMethodManager = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if(isShow) {
            inputManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        } else {
            if(!inputManager.isActive) return
            inputManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun totalWidth() : Int {
        return Apps.context().resources.displayMetrics.widthPixels
    }

    fun totalHeight() : Int {
        return Apps.context().resources.displayMetrics.heightPixels
    }

    fun underLine(tv : TextView) {
        tv.paintFlags = tv.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }
}