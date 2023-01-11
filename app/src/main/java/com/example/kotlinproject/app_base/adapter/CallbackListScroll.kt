package com.example.kotlinproject.app_base.adapter

import androidx.recyclerview.widget.RecyclerView

class CallbackListScroll(callbackScroll: CallbackScroll) : RecyclerView.OnScrollListener() {
    var beforeTotal : Int = 0
    var isLoading : Boolean = false
    var curPage : Int = 0

    var callbackScroll : CallbackScroll ?= callbackScroll
    var listPositionHelper : HelperPosition ?= null

    interface CallbackScroll {
        fun loadMore(curPage : Int)
    }

    init {
        curPage = 0
        beforeTotal = 0
        isLoading = true
    }

    fun initialize() {
        curPage = 0
        beforeTotal = 0
        isLoading = true
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        listPositionHelper = HelperPosition.createHelper(recyclerView)
        val visibleItemCount = recyclerView.childCount
        val totalItemCount: Int = listPositionHelper!!.getItemCount()
        val firstVisibleItem: Int = listPositionHelper!!.findFirstVisibleItemPosition()

        if (isLoading) {
            if (totalItemCount > beforeTotal) {
                isLoading = false
                beforeTotal = totalItemCount
            }
        }
        if (!isLoading && totalItemCount - visibleItemCount <= firstVisibleItem
        ) {
            curPage++
            if(callbackScroll != null) {
                callbackScroll?.let {
                    it.loadMore(curPage)
                }
            }
            isLoading = true
        }
    }
}