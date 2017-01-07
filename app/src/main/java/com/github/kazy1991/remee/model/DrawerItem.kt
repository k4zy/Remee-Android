package com.github.kazy1991.remee.model

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import com.kazy.fontdrawable.FontDrawable


class DrawerItem(val title: String, val colorCode: Int, val fontCode: Char) {

    fun createIcon(context: Context, typeFace: Typeface): Drawable {
        return FontDrawable.Builder(context, fontCode, typeFace)
                .setSizeDp(100)
                .setColor(colorCode)
                .build()
    }

}
