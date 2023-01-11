package com.example.kotlinproject.utils

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.widget.Toast

class UtilsDownload {
    companion object {
        var instance : UtilsDownload ?= null

        fun get() : UtilsDownload {
            synchronized(this) {
                if (instance == null) {
                    instance = UtilsDownload()
                }
                return instance!!
            }
        }
    }

    val completeReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            Toast.makeText(context, "다운로드가 완료되었습니다.", Toast.LENGTH_SHORT).show()
            context.unregisterReceiver(this)
        }
    }

    fun download(context : Context, title : String, description : String, downloadUrl : String) {
        val downloadMgr : DownloadManager = context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        val request : DownloadManager.Request = DownloadManager.Request(Uri.parse(downloadUrl))
        request.setTitle(title)
        request.setDescription(description)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        downloadMgr.enqueue(request)
        context.registerReceiver(completeReceiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }
}