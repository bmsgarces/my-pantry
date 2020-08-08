package org.bizties.mypantry.shared

import android.content.Context
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun Float.printableQuantity(): String {
    return if (this % 1 == 0f) this.toInt().toString() else this.toString()
}

fun <T> List<T>.nullIfEmpty(): List<T>? = if (this.isEmpty()) null else this

fun Context.getDrawableCompat(@DrawableRes resId: Int) = ContextCompat.getDrawable(this, resId)

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}