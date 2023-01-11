package com.example.kotlinproject.app_base.adapter

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class CallbackHelperMove : ItemTouchHelper.Callback() {
    interface ItemTouchHelperAdapter {
        fun onItemMove(fromPos: Int, targetPos: Int)
        fun onItemDismiss(pos: Int)
        fun refresh()
    }

    var adapter: ItemTouchHelperAdapter? = null

    fun HelperListMove(adapter: ItemTouchHelperAdapter?) {
        this.adapter = adapter
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        //이벤트의 방향 설정
        // 아이템 좌우 배치
        val flagDrag = ItemTouchHelper.START or ItemTouchHelper.END //item drag
        val flagSwipe = ItemTouchHelper.UP or ItemTouchHelper.DOWN //item swipe

        // 아이템 상하 배치
        /*
        int flagDrag = ItemTouchHelper.UP | ItemTouchHelper.DOWN; //item drag
        int flagSwipe = ItemTouchHelper.START | ItemTouchHelper.END;//item swipe
        */
        //return makeMovementFlags(flagDrag, flagSwipe); //drag & swipe 사용
        return makeMovementFlags(flagDrag, 0) //swipe 액션 중지
        // return makeMovementFlags(0, flagSwipe); //drag 액션 중지
    }

    override fun onMove(
        recyclerView: RecyclerView,
        fromHolder: RecyclerView.ViewHolder,
        targetHolder: RecyclerView.ViewHolder
    ): Boolean {
        adapter!!.onItemMove(fromHolder.adapterPosition, targetHolder.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
        //adapter의 onItemDismiss 호출
        adapter!!.onItemDismiss(viewHolder.adapterPosition)
    }

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return false
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        if (actionState == ItemTouchHelper.ACTION_STATE_IDLE) {
            adapter!!.refresh()
        }
    }
}