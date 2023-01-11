package com.example.kotlinproject.app_base.adapter

import android.view.View
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView

class HelperPosition(recyclerView: RecyclerView) {
    var recyclerView: RecyclerView? = recyclerView
    var layoutManager: RecyclerView.LayoutManager? = null

    init {
        layoutManager = recyclerView.layoutManager
    }

    companion object {
        fun createHelper(recyclerView: RecyclerView?): HelperPosition? {
            if (recyclerView == null) {
                throw NullPointerException("Recycler View is null")
            }
            return HelperPosition(recyclerView)
        }
    }

    fun getItemCount(): Int {
        var count = 0
        layoutManager?.let {
            count = it.itemCount
        }
        return count
    }

    fun findFirstVisibleItemPosition(): Int {
        val child = findOneVisibleChild(0, layoutManager!!.childCount, false, true)
        return if (child == null) RecyclerView.NO_POSITION else recyclerView!!.getChildAdapterPosition(child)
    }

    fun findFirstCompletelyVisibleItemPosition(): Int {
        val child = findOneVisibleChild(0, layoutManager!!.childCount, true, false)
        return if (child == null) RecyclerView.NO_POSITION else recyclerView!!.getChildAdapterPosition(child)
    }

    fun findLastVisibleItemPosition(): Int {
        val child = findOneVisibleChild(layoutManager!!.childCount - 1, -1, false, true)
        return if (child == null) RecyclerView.NO_POSITION else recyclerView!!.getChildAdapterPosition(child)
    }

    fun findLastCompletelyVisibleItemPosition(): Int {
        val child = findOneVisibleChild(layoutManager!!.childCount - 1, -1, true, false)
        return if (child == null) RecyclerView.NO_POSITION else recyclerView!!.getChildAdapterPosition(child)
    }

    fun findOneVisibleChild(
        fromIndex: Int, toIndex: Int, completelyVisible: Boolean,
        acceptPartiallyVisible: Boolean
    ): View? {
        val helper: OrientationHelper
        helper = if (layoutManager!!.canScrollVertically()) {
            OrientationHelper.createVerticalHelper(layoutManager)
        } else {
            OrientationHelper.createHorizontalHelper(layoutManager)
        }
        val start = helper.startAfterPadding
        val end = helper.endAfterPadding
        val next = if (toIndex > fromIndex) 1 else -1
        var partiallyVisible: View? = null
        var i = fromIndex
        while (i != toIndex) {
            val child = layoutManager!!.getChildAt(i)
            val childStart = helper.getDecoratedStart(child)
            val childEnd = helper.getDecoratedEnd(child)
            if (childStart < end && childEnd > start) {
                if (completelyVisible) {
                    if (childStart >= start && childEnd <= end) {
                        return child
                    } else if (acceptPartiallyVisible && partiallyVisible == null) {
                        partiallyVisible = child
                    }
                } else {
                    return child
                }
            }
            i += next
        }
        return partiallyVisible
    }
}