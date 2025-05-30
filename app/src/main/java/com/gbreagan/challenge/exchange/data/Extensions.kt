package com.gbreagan.challenge.exchange.data

import android.content.Context
import java.util.Locale
import kotlin.math.pow
import kotlin.math.roundToInt

fun Context.loadJsonFromAsset(fileName: String): String? {
    return try {
        assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ex: Exception) {
        ex.printStackTrace()
        null
    }
}

fun Double?.orZero(): Double = this ?: 0.0
fun Double?.toTwoDecimalString(default: String = "-.--"): String {
    return this?.let { String.format(Locale.ROOT, "%.2f", it) } ?: default
}

fun String.isValidDecimal(): Boolean {
    return this.isEmpty() || this.matches(Regex("^\\d*(\\.\\d*)?\$"))
}

fun Double.round(decimals: Int): Double {
    val factor = 10.0.pow(decimals)
    return (this * factor).roundToInt() / factor
}