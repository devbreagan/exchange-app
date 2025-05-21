package com.gbreagan.challenge.exchange.data

import android.content.Context

fun Context.loadJsonFromAsset(fileName: String): String? {
    return try {
        assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ex: Exception) {
        ex.printStackTrace()
        null
    }
}
fun Double?.orZero(): Double = this ?: 0.0