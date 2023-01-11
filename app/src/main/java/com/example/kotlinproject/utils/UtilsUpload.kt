package com.example.kotlinproject.utils

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class UtilsUpload {
    companion object {
        var instance: UtilsUpload ?= null

        fun get(): UtilsUpload {
            synchronized(this) {
                if(instance == null) {
                    instance = UtilsUpload()
                }
                return instance!!
            }
        }
    }

    fun get(
        mediaType:String,
        name:String,
        filePath:String
    ) : MultipartBody.Part {
        val file = File(filePath)
        val req : RequestBody = RequestBody.create(mediaType.toMediaTypeOrNull(), file)
        return MultipartBody.Part.createFormData(name, file.name, req)
    }
}