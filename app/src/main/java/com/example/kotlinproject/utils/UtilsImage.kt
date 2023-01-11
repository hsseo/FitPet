package com.example.kotlinproject.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class UtilsImage {
    companion object {
        fun set(context : Context, iv : ImageView, uri : Uri) {
            Glide.with(context).load(uri).into(iv)
        }

        fun set(context : Context, iv : ImageView, url : String) {
            Glide.with(context).load(url).into(iv)
        }
    }
}