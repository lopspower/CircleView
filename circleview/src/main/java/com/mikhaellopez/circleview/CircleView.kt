package com.mikhaellopez.circleview

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import kotlin.math.min

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
    private val paintShadow: Paint = Paint().apply { isAntiAlias = true }
    private var circleCenter = 0
    private var heightCircle = 0

    //region Attributes
    var circleColor: Int = Color.WHITE
        set(value) {
            field = value
            manageCircleColor()
            invalidate()
        }
    var circleColorStart: Int? = null
        set(value) {
            field = value
            manageCircleColor()
            invalidate()
        }
    var circleColorEnd: Int? = null
        set(value) {
            field = value
            manageCircleColor()
            invalidate()
        }
    var circleColorDirection: GradientDirection = GradientDirection.LEFT_TO_RIGHT
        set(value) {
            field = value
            manageCircleColor()
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
            manageBorderColor()
            invalidate()
        }
    var borderColorStart: Int? = null
        set(value) {
            field = value
            manageBorderColor()
            invalidate()
        }
    var borderColorEnd: Int? = null
        set(value) {
            field = value
            manageBorderColor()
            invalidate()
        }
    var borderColorDirection: GradientDirection = GradientDirection.LEFT_TO_RIGHT
        set(value) {
            field = value
            manageBorderColor()
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
            paintShadow.color = field
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
        attributes.getColor(R.styleable.CircleView_cv_color_start, 0)
                .also { if (it != 0) circleColorStart = it }
        attributes.getColor(R.styleable.CircleView_cv_color_end, 0)
                .also { if (it != 0) circleColorEnd = it }
        circleColorDirection = attributes.getInteger(R.styleable.CircleView_cv_color_direction, circleColorDirection.value).toGradientDirection()

        // Init Border
        if (attributes.getBoolean(R.styleable.CircleView_cv_border, false)) {
            val defaultBorderSize = DEFAULT_BORDER_WIDTH * resources.displayMetrics.density
            borderWidth = attributes.getDimension(R.styleable.CircleView_cv_border_width, defaultBorderSize)
            borderColor = attributes.getColor(R.styleable.CircleView_cv_border_color, borderColor)
            attributes.getColor(R.styleable.CircleView_cv_border_color_start, 0)
                    .also { if (it != 0) borderColorStart = it }
            attributes.getColor(R.styleable.CircleView_cv_border_color_end, 0)
                    .also { if (it != 0) borderColorEnd = it }
            borderColorDirection = attributes.getInteger(R.styleable.CircleView_cv_border_color_direction, borderColorDirection.value).toGradientDirection()
        }

        // Init Shadow
        shadowEnable = attributes.getBoolean(R.styleable.CircleView_cv_shadow, shadowEnable)
        if (shadowEnable) {
            shadowColor = attributes.getColor(R.styleable.CircleView_cv_shadow_color, shadowColor)
            shadowGravity = attributes.getInteger(R.styleable.CircleView_cv_shadow_gravity, shadowGravity.value).toShadowGravity()
            shadowRadius = attributes.getFloat(R.styleable.CircleView_cv_shadow_radius, DEFAULT_SHADOW_RADIUS)
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
        if (shadowEnable) {
            drawShadow()
            canvas.drawCircle(circleCenterWithBorder, circleCenterWithBorder, circleCenterWithBorder - margeWithShadowRadius, paintShadow)
        }

        // Draw Border
        canvas.drawCircle(circleCenterWithBorder, circleCenterWithBorder, circleCenterWithBorder - margeWithShadowRadius, paintBorder)
        // Draw Circle background
        canvas.drawCircle(circleCenterWithBorder, circleCenterWithBorder, circleCenter - margeWithShadowRadius, paint)
    }

    private fun update() {
        val usableWidth = width - (paddingLeft + paddingRight)
        val usableHeight = height - (paddingTop + paddingBottom)
        heightCircle = min(usableWidth, usableHeight)

        circleCenter = (heightCircle - borderWidth * 2).toInt() / 2
        manageCircleColor()
        manageBorderColor()
        manageElevation()
        invalidate()
    }

    private fun manageCircleColor() {
        paint.shader = createLinearGradient(circleColorStart ?: circleColor,
                circleColorEnd ?: circleColor, circleColorDirection)
    }

    private fun manageBorderColor() {
        val borderColor = if (borderWidth == 0f) circleColor else this.borderColor
        paintBorder.shader = createLinearGradient(borderColorStart ?: borderColor,
                borderColorEnd ?: borderColor, borderColorDirection)
    }

    private fun createLinearGradient(startColor: Int, endColor: Int, gradientDirection: GradientDirection): LinearGradient {
        var x0 = 0f
        var y0 = 0f
        var x1 = 0f
        var y1 = 0f
        when (gradientDirection) {
            GradientDirection.LEFT_TO_RIGHT -> x1 = width.toFloat()
            GradientDirection.RIGHT_TO_LEFT -> x0 = width.toFloat()
            GradientDirection.TOP_TO_BOTTOM -> y1 = height.toFloat()
            GradientDirection.BOTTOM_TO_TOP -> y0 = height.toFloat()
        }
        return LinearGradient(x0, y0, x1, y1, startColor, endColor, Shader.TileMode.CLAMP)
    }

    private fun manageElevation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            outlineProvider = if (!shadowEnable) object : ViewOutlineProvider() {
                override fun getOutline(view: View?, outline: Outline?) {
                    outline?.setOval(0, 0, heightCircle, heightCircle)
                }
            } else null
        }
    }

    private fun drawShadow() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
            setLayerType(LAYER_TYPE_SOFTWARE, paintShadow)
        }

        var dx = 0f
        var dy = 0f

        when (shadowGravity) {
            ShadowGravity.CENTER -> {
                /*dx, dy = 0.0f*/
            }
            ShadowGravity.TOP -> dy = -shadowRadius / 2
            ShadowGravity.BOTTOM -> dy = shadowRadius / 2
            ShadowGravity.START -> dx = -shadowRadius / 2
            ShadowGravity.END -> dx = shadowRadius / 2
        }

        paintShadow.setShadowLayer(shadowRadius, dx, dy, shadowColor)
    }

    private fun Int.toShadowGravity(): ShadowGravity =
            when (this) {
                1 -> ShadowGravity.CENTER
                2 -> ShadowGravity.TOP
                3 -> ShadowGravity.BOTTOM
                4 -> ShadowGravity.START
                5 -> ShadowGravity.END
                else -> throw IllegalArgumentException("This value is not supported for ShadowGravity: $this")
            }

    private fun Int.toGradientDirection(): GradientDirection =
            when (this) {
                1 -> GradientDirection.LEFT_TO_RIGHT
                2 -> GradientDirection.RIGHT_TO_LEFT
                3 -> GradientDirection.TOP_TO_BOTTOM
                4 -> GradientDirection.BOTTOM_TO_TOP
                else -> throw IllegalArgumentException("This value is not supported for GradientDirection: $this")
            }

    /**
     * ShadowGravity enum class to set the gravity of the CircleView shadow
     */
    enum class ShadowGravity(val value: Int) {
        CENTER(1),
        TOP(2),
        BOTTOM(3),
        START(4),
        END(5)
    }

    /**
     * GradientDirection enum class to set the direction of the gradient color
     */
    enum class GradientDirection(val value: Int) {
        LEFT_TO_RIGHT(1),
        RIGHT_TO_LEFT(2),
        TOP_TO_BOTTOM(3),
        BOTTOM_TO_TOP(4)
    }

}
