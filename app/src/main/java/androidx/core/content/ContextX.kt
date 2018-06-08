package androidx.core.content

import android.content.Context
import android.support.annotation.DimenRes

fun Context.dimen(@DimenRes id: Int): Int = resources.getDimensionPixelSize(id)

fun Context.dip(size: Int): Int = (resources.displayMetrics.density * size).toInt()
