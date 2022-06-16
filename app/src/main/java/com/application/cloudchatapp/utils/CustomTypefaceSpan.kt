package com.application.cloudchatapp.utils

import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.MetricAffectingSpan

class CustomTypefaceSpan(typeface: Typeface?): MetricAffectingSpan() {
    private val fontTypeFace: Typeface? = typeface

    override fun updateDrawState(ds: TextPaint) {
        applyCustomTypeFace(ds, fontTypeFace)
    }

    override fun updateMeasureState(paint: TextPaint) {
        applyCustomTypeFace(paint, fontTypeFace)
    }

    private fun applyCustomTypeFace(paint: Paint, tf: Typeface?) {
        paint.typeface = tf
    }
}