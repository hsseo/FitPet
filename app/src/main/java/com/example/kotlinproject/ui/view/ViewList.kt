package com.example.kotlinproject.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.kotlinproject.databinding.ViewListBinding

class ViewList : RelativeLayout {
    constructor(
        context: Context, attrs: AttributeSet? = null
    ) : super(context, attrs) {
    }
    constructor(
        context : Context, attrs: AttributeSet? = null, defStyleAttr : Int
    ) : super(context, attrs, defStyleAttr) {
    }
    constructor(
        context : Context, attrs: AttributeSet? = null, defStyleAttr : Int, defStyleRes : Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
    }

    var _binding :ViewListBinding? = null
    val binding get() = _binding!!
    var isPWDAble : Boolean = false

    init {
        _binding = ViewListBinding.inflate(LayoutInflater.from(context), this, true)
        initialize()
    }

    fun initialize() {
        binding.swipeList.isEnabled = false
    }

    fun emptyMsg(msg : String) {
        binding.tvEmptyMessage.text = msg
    }

    fun emptyMsg(msg : String, iconId:Int) {
        binding.tvEmptyMessage.text = msg
        binding.ivEmptyMessage.setImageResource(iconId)
    }

    fun refreshSwipe(listener:SwipeRefreshLayout.OnRefreshListener) {
        binding.swipeList.isEnabled = true
        binding.swipeList.setOnRefreshListener {
            listener.onRefresh()
        }
    }

    fun completeRefresh() {
        binding.swipeList.isRefreshing = false
    }

    fun emptyList(isEmpty : Boolean) {
        binding.layerEmpty.visibility = if (isEmpty) View.VISIBLE else View.GONE
    }

    fun emptyList(itemCnt : Int) {
        binding.layerEmpty.visibility = if (itemCnt > 0) View.VISIBLE else View.GONE
    }

    fun setRefreshSwipe(listener: OnRefreshListener?) {
        binding.swipeList.setEnabled(true)
        binding.swipeList.setOnRefreshListener(OnRefreshListener { listener?.onRefresh() })
    }
}