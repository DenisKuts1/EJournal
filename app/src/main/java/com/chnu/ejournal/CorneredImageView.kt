package com.chnu.ejournal

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.ImageView
import android.R.attr.radius
import android.graphics.Path
import android.graphics.RectF
import android.util.TypedValue

/**
 * Custom ImageView, created to add rounded corners to image
 */

class CorneredImageView : ImageView {
    val cornersRadius: Float

    init {
        val dip = 10f
        cornersRadius = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                resources.displayMetrics
        )
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    val clipPath = Path()
    val rect = RectF(0f, 0f, width.toFloat(), height.toFloat())
    override fun onDraw(canvas: Canvas?) {
        rect.right = width.toFloat()
        rect.bottom = height.toFloat()
        clipPath.addRoundRect(rect, cornersRadius, cornersRadius, Path.Direction.CW)
        canvas!!.clipPath(clipPath)

        super.onDraw(canvas)
    }

}