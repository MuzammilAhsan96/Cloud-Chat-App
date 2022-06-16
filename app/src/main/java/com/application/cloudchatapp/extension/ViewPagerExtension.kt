package com.application.cloudchatapp.extension

import androidx.viewpager.widget.ViewPager

fun ViewPager.onPageChanged(listener: (Int,Float,Int) -> Unit) {
    this.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{

        override fun onPageSelected(position: Int) {
        }
        override fun onPageScrollStateChanged(state: Int) {
        }
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            listener(position,positionOffset,positionOffsetPixels)
        }
    })

}