package com.mikhaellopez.circleview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Copyright (C) 2019 Mikhael LOPEZ
 * Licensed under the Apache License Version 2.0
 */
class CircleView constructor(context: Context, attrs: AttributeSet) : View(context, attrs) {

    companion object {
        private const val DEFAULT_BORDER_WIDTH = 4f
        private const val DEFAULT_SHADOW_RADIUS = 8.0f
    }

    // Paint
    private var paint: Paint = Paint().apply { isAntiAlias = true }
    private var paintBorder: Paint = Paint().apply { isAntiAlias = true }

    //region Properties
    var circleColor: Int = Color.WHITE
        set(value) {
            field = value
            paint.color = field
            invalidate()
        }
    var borderWidth: Float = 0.toFloat()
        set(value) {
            field = value
            invalidate()
        }
    var borderColor: Int = Color.BLACK
        set(value) {
            field = value
            paintBorder.color = borderColor
            invalidate()
        }
    var shadowRadius: Float = 0.toFloat()
        set(value) {
            field = value
            invalidate()
        }
    var shadowColor = Color.BLACK
        set(value) {
            field = value
            invalidate()
        }
    var shadowGravity = ShadowGravity.BOTTOM
        set(value) {
            field = value
            invalidate()
        }

    fun addShadow() {
        if (shadowRadius == 0f)
            shadowRadius = DEFAULT_SHADOW_RADIUS
        invalidate()
    }
    //endregion

    init {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        // Load the styled attributes and set their properties
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CircleView, 0, 0)

        // Init Border
        if (attributes.getBoolean(R.styleable.CircleView_cv_border, false)) {
            val defaultBorderSize = DEFAULT_BORDER_WIDTH * resources.displayMetrics.density
            borderWidth = attributes.getDimension(R.styleable.CircleView_cv_border_width, defaultBorderSize)
            borderColor = attributes.getColor(R.styleable.CircleView_cv_border_color, Color.BLACK)
        }

        circleColor = attributes.getColor(R.styleable.CircleView_cv_color, Color.WHITE)

        // Init Shadow
        if (attributes.getBoolean(R.styleable.CircleView_cv_shadow, false)) {
            val shadowGravityIntValue = attributes.getInteger(R.styleable.CircleView_cv_shadow_gravity, ShadowGravity.BOTTOM.value)
            shadowGravity = ShadowGravity.fromValue(shadowGravityIntValue)
            shadowRadius = attributes.getFloat(R.styleable.CircleView_cv_shadow_radius, DEFAULT_SHADOW_RADIUS)
            shadowColor = attributes.getColor(R.styleable.CircleView_cv_shadow_color, shadowColor)
        }

        attributes.recycle()
    }

    //region Draw Method
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val usableWidth = width - paddingLeft + paddingRight
        val usableHeight = height - paddingTop + paddingTop

        val circleCenter = (Math.min(usableWidth, usableHeight) - borderWidth * 2).toInt() / 2
        val margeWithShadowRadius = shadowRadius * 2

        val cx = circleCenter + borderWidth
        val cy = circleCenter + borderWidth

        // Draw Shadow
        drawShadow()
        // Draw Border
        canvas.drawCircle(cx, cy, circleCenter + borderWidth - margeWithShadowRadius, paintBorder)
        // Draw Circle background
        canvas.drawCircle(cx, cy, circleCenter - margeWithShadowRadius, paint)
    }

    private fun drawShadow() {
        var dx = 0.0f
        var dy = 0.0f

        when (shadowGravity) {
            ShadowGravity.CENTER -> {
                /*dx, dy = 0.0f*/
            }
            ShadowGravity.TOP -> dy = -shadowRadius / 2
            ShadowGravity.BOTTOM -> dy = shadowRadius / 2
            ShadowGravity.START -> dx = -shadowRadius / 2
            ShadowGravity.END -> dx = shadowRadius / 2
        }

        paintBorder.setShadowLayer(shadowRadius, dx, dy, shadowColor)

    }
    //endregion

    enum class ShadowGravity(val value: Int) {
        CENTER(1),
        TOP(2),
        BOTTOM(3),
        START(4),
        END(5);

        companion object {
            fun fromValue(value: Int): ShadowGravity =
                    when (value) {
                        1 -> CENTER
                        2 -> TOP
                        3 -> BOTTOM
                        4 -> START
                        5 -> END
                        else -> throw IllegalArgumentException("This value is not supported for ShadowGravity: $value")
                    }
        }
    }

}
