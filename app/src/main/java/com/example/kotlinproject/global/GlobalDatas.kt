package com.example.kotlinproject.global

class GlobalDatas {
    companion object {
        var instance : GlobalDatas? = null

        fun get() : GlobalDatas {
            synchronized(this) {
                if (instance == null) {
                    instance = GlobalDatas()
                }
                return instance as GlobalDatas
            }
        }

        fun clear() {
            instance = null
        }
    }
}