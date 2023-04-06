package com.abbas.gamesign.utils

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics

object Utility {

    fun getScreenHeightInDp(context: Context): Float {
        val displayMetrics: DisplayMetrics = context.resources.displayMetrics
        return displayMetrics.heightPixels / displayMetrics.density
    }

    fun getScreenWidthInDp(context: Context): Float {
        val displayMetrics: DisplayMetrics = context.resources.displayMetrics
        return displayMetrics.widthPixels / displayMetrics.density
    }

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    fun pxToDp(px: Int): Int {
        return (px / Resources.getSystem().displayMetrics.density).toInt()
    }

    fun pxToSp(px: Int): Float {
        return px / Resources.getSystem().displayMetrics.scaledDensity
    }


}