package com.application.cloudchatapp.extension

import com.google.android.material.navigation.NavigationView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable

fun NavigationView.changeCornerRadius() {
    val navViewBackground : MaterialShapeDrawable = background as MaterialShapeDrawable
    val radius = resources.getDimension(com.intuit.sdp.R.dimen._8sdp)
    navViewBackground.shapeAppearanceModel = navViewBackground.shapeAppearanceModel.toBuilder()
        .setTopRightCorner(CornerFamily.CUT, radius)
        .setTopLeftCorner(CornerFamily.CUT,radius)
        .setBottomRightCorner(CornerFamily.ROUNDED,radius)
        .build()
}