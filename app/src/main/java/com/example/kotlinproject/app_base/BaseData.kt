package com.example.kotlinproject.app_base

import androidx.room.Ignore

open class BaseData : java.io.Serializable {
    @Ignore var eventId = -1
}