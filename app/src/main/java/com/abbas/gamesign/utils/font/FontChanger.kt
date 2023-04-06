package com.abbas.gamesign.utils.font

import android.content.Context
import android.graphics.Typeface
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class FontChanger @Inject constructor(@ApplicationContext val context: Context) {


    fun getFont(fontStyle: FontStyle): Typeface {
        return getCachedFont(fontStyle)
    }

    private fun getCachedFont(fontStyle: FontStyle): Typeface {

        val typeFaces: HashMap<String, Typeface> = HashMap()
        val typeFace: Typeface?
        val fontFileName: String = when (fontStyle) {
            FontStyle.MEDIUM -> "Medium"
            FontStyle.BOLD -> "Bold"
//                FontStyle.LIGHT -> "Light"
        }

        if (typeFaces.containsKey(fontStyle.name)) {
            typeFace = typeFaces[fontStyle.name]!!
        } else {
            typeFace = Typeface.createFromAsset(context.assets, "fonts/Roboto_$fontFileName.ttf")
            typeFaces[fontStyle.name] = typeFace
        }

        return typeFace!!
    }

}