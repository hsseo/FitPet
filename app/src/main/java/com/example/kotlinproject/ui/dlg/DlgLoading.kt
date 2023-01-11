package com.example.kotlinproject.ui.dlg

import android.app.Dialog
import androidx.fragment.app.FragmentActivity
import android.text.TextUtils
import android.view.View
import android.view.Window
import com.example.kotlinproject.databinding.DlgLoadingBinding

class DlgLoading(private val context: FragmentActivity) {
    private var _binding : DlgLoadingBinding? = null
    private val binding get() = _binding!!

    private val dlg = Dialog(context, android.R.style.Theme_Translucent_NoTitleBar)

    fun init() {
        if(_binding == null) {
            _binding = DlgLoadingBinding.inflate(context.layoutInflater)
            dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dlg.setContentView(binding.root)
            dlg.setCancelable(false)
        }
    }

    fun dismiss() {
        try {
            dlg.dismiss()
        } catch (err:Exception) {}
    }

    fun message(message:String) {
        init()
        if(TextUtils.isEmpty(message)) {
            binding.progressText.visibility = View.GONE
        } else {
            binding.progressText.visibility = View.VISIBLE
            binding.progressText.text = message;
        }
        dlg.show()
    }
}