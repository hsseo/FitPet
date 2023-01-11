package com.example.kotlinproject.utils

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore

class UtilsFile {
    companion object {
        var instance: UtilsFile? = null

        fun get(): UtilsFile {
            synchronized(this) {
                if (instance == null) {
                    instance = UtilsFile()
                }
                return instance!!
            }
        }
    }

    private fun isExtStorageDoc(uri : Uri) : Boolean {
        return uri.authority.equals("com.android.externalstorage.documents")
    }
    private fun isDownloadDoc(uri : Uri) : Boolean {
        return uri.authority.equals("com.android.providers.downloads.documents")
    }
    private fun isMediaDoc(uri : Uri) : Boolean {
        return uri.authority.equals("com.android.providers.media.documents")
    }
    private fun column(context:Context, uri:Uri, sel:String, vararg args:String) : String{
        var cursor : Cursor ?= null
        val column = "_data"
        try {
            cursor = context.contentResolver.query(uri, arrayOf(column), sel, args, null)
            if(cursor != null && cursor.moveToFirst()) {
                return cursor.getString(cursor.getColumnIndexOrThrow(column))
            }
        } catch (ignore : Exception) {

        } finally {
            if(cursor != null) {
                cursor.close()
            }
        }
        return ""
    }

    fun uriToFilePath(context: Context, uri: Uri) : String {
        try {
            if (DocumentsContract.isDocumentUri(context, uri)) {
                if (isExtStorageDoc(uri)) {
                    val docId = DocumentsContract.getDocumentId(uri)
                    val split = docId.split(":").toTypedArray()
                    val type = split[0]
                    if ("primary".equals(type, ignoreCase = true)) {
                        return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
                    }
                } else if (isDownloadDoc(uri)) {
                    val id = DocumentsContract.getDocumentId(uri)
                    var contentUri: Uri? = null
                    contentUri = try {
                        ContentUris.withAppendedId(
                            Uri.parse("content://downloads/public_downloads"), id.toLong()
                        )
                    } catch (e: java.lang.Exception) {
                        uri
                    }
                    return column(context,
                        contentUri!!,
                        "",
                        ""
                    )
                } else if (isMediaDoc(uri)) {
                    // MediaProvider
                    val docId = DocumentsContract.getDocumentId(uri)
                    val split = docId.split(":").toTypedArray()
                    val type = split[0]
                    var contentUri: Uri? = null
                    if ("image" == type) {
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    } else if ("video" == type) {
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                    } else if ("audio" == type) {
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                    }
                    val selection = "_id=?"
                    val selectionArgs = arrayOf(
                        split[1]
                    )
                    return column(
                        context,
                        contentUri!!,
                        selection,
                        *selectionArgs
                    )
                }
            } else if ("content".equals(uri.scheme, ignoreCase = true)) {
                return column(
                    context,
                    uri,
                    "",
                    ""
                )
            } else if ("file".equals(uri.scheme, ignoreCase = true)) {
                return uri.path!!
            }
        } catch (ignore: java.lang.Exception) {
        }
        return ""
    }
}