package com.application.cloudchatapp.extension

import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import com.application.cloudchatapp.utils.AppConstant

fun ImageView.rotateImage() {
    val rotate = RotateAnimation(
        0F,
        180F,
        Animation.RELATIVE_TO_SELF,
        0.5f,
        Animation.RELATIVE_TO_SELF,
        0.5f
    )
    rotate.duration = AppConstant.SPLASH_DELAY
    rotate.interpolator = LinearInterpolator()
    this.startAnimation(rotate)
}
