package com.example.kotlinproject.utils

import android.util.Log
import com.example.kotlinproject.constants.ConstAPP

class UtilsLog {
    companion object {
        fun v(tag:String, msg:String) {
            if(ConstAPP.IS_DEBUG) {
                Log.v(tag, msg)
            }
        }
        fun d(tag:String, msg:String) {
            if(ConstAPP.IS_DEBUG) {
                Log.d(tag, msg)
            }
        }
        fun i(tag:String, msg:String) {
            if(ConstAPP.IS_DEBUG) {
                Log.i(tag, msg)
            }
        }
        fun e(tag:String, msg:String) {
            if(ConstAPP.IS_DEBUG) {
                Log.e(tag, msg)
            }
        }
        fun error(tag:String, title:String, msg:String) {
            if(ConstAPP.IS_DEBUG) {
                Log.e(tag, "----------------------------------------------------------")
                Log.e(tag, title)
                Log.e(tag, msg)
                Log.e(tag, "----------------------------------------------------------")
            }
        }
    }


}