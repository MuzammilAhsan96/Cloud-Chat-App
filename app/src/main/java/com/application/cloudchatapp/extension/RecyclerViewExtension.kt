package com.application.cloudchatapp.extension

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView


fun RecyclerView.smoothSnapToPosition(
    position: Int,
    snapMode: Int = LinearSmoothScroller.SNAP_TO_START
) {
    val smoothScroller = object : LinearSmoothScroller(this.context) {
        override fun getVerticalSnapPreference(): Int = snapMode
        override fun getHorizontalSnapPreference(): Int = snapMode
    }
    smoothScroller.targetPosition = position
    layoutManager?.startSmoothScroll(smoothScroller)
}

fun RecyclerView.centerSmoothScroller(position: Int) {
    try {
        val smoothScroller = object : LinearSmoothScroller(this.context) {
            override fun calculateDtToFit(
                viewStart: Int,
                viewEnd: Int,
                boxStart: Int,
                boxEnd: Int,
                snapPreference: Int
            ): Int {
                return (boxStart + (boxEnd - boxStart) / 2) - (viewStart + (viewEnd - viewStart) / 2)
            }
        }
        smoothScroller.targetPosition = position
        layoutManager?.startSmoothScroll(smoothScroller)
    } catch (e: Exception) {

    }
}


fun RecyclerView.spanIndex(position: Int?): Int {
    val viewHolder: RecyclerView.ViewHolder? =
        this.findViewHolderForAdapterPosition(position ?: 0)
    val lp = viewHolder?.itemView?.layoutParams as GridLayoutManager.LayoutParams
    return lp.spanIndex
}