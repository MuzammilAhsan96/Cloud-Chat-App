package com.application.cloudchatapp.extension

import java.math.RoundingMode
import java.text.DecimalFormat

fun Double.roundOffDecimal(): Double? {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.FLOOR
    return df.format(this).toDoubleOrNull()
}
