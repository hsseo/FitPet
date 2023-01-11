package com.example.kotlinproject.app_base.adapter

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.kotlinproject.app_base.BaseData
import com.example.kotlinproject.ui.view.ViewList
import io.reactivex.disposables.CompositeDisposable

abstract class BaseAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>(), CallbackListScroll.CallbackScroll {
    var context : Context ?= null

    var subscription : CompositeDisposable = CompositeDisposable()

    var curPage : Int = 0
    var list : ViewList ?= null
    var listenerScroll : CallbackListScroll ?= null

    var listener : CallbackAdapter ?= null
    var datas : ArrayList<BaseData> = ArrayList()

    init {
        listenerScroll = CallbackListScroll(this)
    }

    constructor(context : Context) : this() {
        this.context = context
    }

    override fun loadMore(curPage: Int) {
        if(this.curPage != curPage) {
            this.curPage = curPage
            if (listener != null) {
                listener!!.listMore(curPage)
            }
        }
    }

    fun clear() {
        curPage = 0
    }

    fun vertical(isRefresh : Boolean, list : ViewList) {
        this.list = list
        list.binding.recyclerList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        list.binding.recyclerList.setHasFixedSize(true)
        list.binding.recyclerList.adapter = this
        listenerScroll!!.initialize()
        list.binding.recyclerList.setOnScrollListener(listenerScroll)
        clear()
        if(isRefresh) {
            list.setRefreshSwipe(SwipeRefreshLayout.OnRefreshListener {
                if(listener != null) {
                    listener!!.listRefresh()
                }
            })
        }
    }

    fun horizontal(isRefresh : Boolean, list : ViewList) {
        this.list = list
        list.binding.recyclerList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        list.binding.recyclerList.setHasFixedSize(true)
        list.binding.recyclerList.adapter = this
        listenerScroll!!.initialize()
        list.binding.recyclerList.setOnScrollListener(listenerScroll)
        clear()
        if(isRefresh) {
            list.setRefreshSwipe(SwipeRefreshLayout.OnRefreshListener {
                if(listener != null) {
                    listener!!.listRefresh()
                }
            })
        }
    }
    fun grid(isRefresh: Boolean, list:ViewList, spanCnt : Int, spanSizeLookup : GridLayoutManager.SpanSizeLookup) {
        this.list = list
        var manager = GridLayoutManager(context, spanCnt)
        if(spanSizeLookup != null) {
            manager.spanSizeLookup = spanSizeLookup
        }
        list.binding.recyclerList.layoutManager = manager
        list.binding.recyclerList.setHasFixedSize(true)
        list.binding.recyclerList.adapter = this

        listenerScroll!!.initialize()
        list.binding.recyclerList.setOnScrollListener(listenerScroll)
        clear()
        if(isRefresh) {
            list.setRefreshSwipe(SwipeRefreshLayout.OnRefreshListener {
                if(listener != null) {
                    listener!!.listRefresh()
                }
            })
        }
    }
}