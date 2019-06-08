package com.mikhaellopez.circleview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Outline
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider

/**
 * Copyright (C) 2019 Mikhael LOPEZ
 * Licensed under the Apache License Version 2.0
 */
class CircleView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    companion object {
        private const val DEFAULT_BORDER_WIDTH = 4f
        private const val DEFAULT_SHADOW_RADIUS = 8.0f
    }

    // Properties
    private val paint: Paint = Paint().apply { isAntiAlias = true }
    private val paintBorder: Paint = Paint().apply { isAntiAlias = true }
    private var circleCenter = 0
    private var heightCircle = 0

    //region Attributes
    var circleColor: Int = Color.WHITE
        set(value) {
            field = value
            paint.color = field
            invalidate()
        }
    var borderWidth: Float = 0f
        set(value) {
            field = value
            update()
        }
    var borderColor: Int = Color.BLACK
        set(value) {
            field = value
            invalidate()
        }
    var shadowRadius: Float = 0f
        set(value) {
            field = value
            shadowEnable = shadowRadius > 0f
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
    var shadowEnable = false
        set(value) {
            field = value
            if (field && shadowRadius == 0f)
                shadowRadius = DEFAULT_SHADOW_RADIUS
            update()
        }
    //endregion

    init {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        // Load the styled attributes and set their properties
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CircleView, 0, 0)

        // Init Circle Color
        circleColor = attributes.getColor(R.styleable.CircleView_cv_color, Color.WHITE)

        // Init Border
        if (attributes.getBoolean(R.styleable.CircleView_cv_border, false)) {
            val defaultBorderSize = DEFAULT_BORDER_WIDTH * resources.displayMetrics.density
            borderWidth = attributes.getDimension(R.styleable.CircleView_cv_border_width, defaultBorderSize)
            borderColor = attributes.getColor(R.styleable.CircleView_cv_border_color, Color.BLACK)
        }

        // Init Shadow
        shadowEnable = attributes.getBoolean(R.styleable.CircleView_cv_shadow, shadowEnable)
        if (shadowEnable) {
            val shadowGravityIntValue = attributes.getInteger(R.styleable.CircleView_cv_shadow_gravity, ShadowGravity.BOTTOM.value)
            shadowGravity = ShadowGravity.fromValue(shadowGravityIntValue)
            shadowRadius = attributes.getFloat(R.styleable.CircleView_cv_shadow_radius, DEFAULT_SHADOW_RADIUS)
            shadowColor = attributes.getColor(R.styleable.CircleView_cv_shadow_color, shadowColor)
        }

        attributes.recycle()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        update()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val circleCenterWithBorder = circleCenter + borderWidth
        val margeWithShadowRadius = if (shadowEnable) shadowRadius * 2 else 0f

        // Draw Shadow
        if (shadowEnable) drawShadow()
        // Draw Border
        canvas.drawCircle(circleCenterWithBorder, circleCenterWithBorder, circleCenterWithBorder - margeWithShadowRadius, paintBorder)
        // Draw Circle background
        canvas.drawCircle(circleCenterWithBorder, circleCenterWithBorder, circleCenter - margeWithShadowRadius, paint)
    }

    private fun update() {
        val usableWidth = width - (paddingLeft + paddingRight)
        val usableHeight = height - (paddingTop + paddingBottom)
        heightCircle = Math.min(usableWidth, usableHeight)

        circleCenter = (heightCircle - borderWidth * 2).toInt() / 2
        paintBorder.color = if (borderWidth == 0f) circleColor else borderColor

        manageElevation()
        invalidate()
    }

    private fun manageElevation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            outlineProvider = if (!shadowEnable) object : ViewOutlineProvider() {
                override fun getOutline(view: View?, outline: Outline?) {
                    outline?.setOval(0, 0, heightCircle, heightCircle)
                }
            } else {
                null
            }
        }
    }

    private fun drawShadow() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
            setLayerType(LAYER_TYPE_SOFTWARE, paintBorder)
        }

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
