package com.robertlevonyan.views.chip

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat

class Chip : AppCompatTextView {
    var chipIcon: Drawable? = null
        set(value) {
            field = value
            buildView()
        }
    var chipIconBitmap: Bitmap? = null
        set(value) {
            field = value
            buildView()
        }
    var closable = false
        set(value) {
            field = value
            if (value) selectable = false
            buildView()
        }
    var selectable = false
        set(value) {
            field = value
            if (value) closable = false
            buildView()
        }
    var chipSelected = false
        set(value) {
            if (closable || selectable) {
                field = value
                buildView()
            }
        }
    var chipBackgroundColor = 0
        set(value) {
            field = value
            buildView()
        }
    var chipSelectedBackgroundColor = 0
        set(value) {
            field = value
            buildView()
        }
    var chipTextColor = 0
        set(value) {
            field = value
            buildView()
        }
    var chipSelectedTextColor = 0
        set(value) {
            field = value
            buildView()
        }
    var chipCloseColor = 0
        set(value) {
            field = value
            buildView()
        }
    var chipSelectedCloseColor = 0
        set(value) {
            field = value
            buildView()
        }
    var cornerRadius = 0
        set(value) {
            field = value
            buildView()
        }
    var strokeSize = 0
        set(value) {
            field = value
            buildView()
        }
    var strokeColor = 0
        set(value) {
            field = value
            buildView()
        }

    var selectedStrokeColor = 0
        set(value) {
            field = value
            buildView()
        }

    var selectedStrokeSize = 0
        set(value) {
            field = value
            buildView()
        }

    var chipSelectableWithoutIcon = false
        set(value) {
            field = value
            buildView()
        }
    private var iconText: String? = null
    private var iconTextColor = 0
    private var iconTextBackgroundColor = 0

    var onCloseClickListener: OnCloseClickListener? = null
    var onSelectClickListener: OnSelectClickListener? = null
    var onIconClickListener: OnIconClickListener? = null

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        attrs?.let { initTypedArray(it) } ?: initDefaultValues()
        buildView()
        setSingleLine()
        ellipsize = TextUtils.TruncateAt.END
    }

    private fun initTypedArray(attrs: AttributeSet) {
        if (context == null) return

        val ta = context.theme.obtainStyledAttributes(attrs, R.styleable.Chip, 0, 0)

        chipIcon = ta.getDrawable(R.styleable.Chip_mcv_chipIcon)
        closable = ta.getBoolean(R.styleable.Chip_mcv_closable, false)
        selectable = ta.getBoolean(R.styleable.Chip_mcv_selectable, false)
        chipBackgroundColor = ta.getColor(R.styleable.Chip_mcv_backgroundColor, ContextCompat.getColor(context, R.color.colorChipBackground))
        chipSelectedBackgroundColor = ta.getColor(R.styleable.Chip_mcv_selectedBackgroundColor, ContextCompat.getColor(context, R.color.colorChipBackgroundClicked))
        chipTextColor = ta.getColor(R.styleable.Chip_mcv_textColor, ContextCompat.getColor(context, R.color.colorChipText))
        chipSelectedTextColor = ta.getColor(R.styleable.Chip_mcv_selectedTextColor, ContextCompat.getColor(context, R.color.colorChipTextClicked))
        chipCloseColor = ta.getColor(R.styleable.Chip_mcv_closeColor, ContextCompat.getColor(context, R.color.colorChipCloseInactive))
        chipSelectedCloseColor = ta.getColor(R.styleable.Chip_mcv_selectedCloseColor, ContextCompat.getColor(context, R.color.colorChipCloseClicked))
        cornerRadius = ta.getDimensionPixelSize(R.styleable.Chip_mcv_cornerRadius, resources.getDimensionPixelSize(R.dimen.chip_height) / 2)
        strokeSize = ta.getDimensionPixelSize(R.styleable.Chip_mcv_strokeSize, 0)
        strokeColor = ta.getColor(R.styleable.Chip_mcv_strokeColor, ContextCompat.getColor(context, R.color.colorChipCloseClicked))
        selectedStrokeSize = ta.getDimensionPixelSize(R.styleable.Chip_mcv_selectedStrokeSize, 0)
        selectedStrokeColor = ta.getColor(R.styleable.Chip_mcv_selectedStrokeColor, ContextCompat.getColor(context, R.color.colorChipCloseInactive))
        val iconText = ta.getString(R.styleable.Chip_mcv_iconText)
        val iconTextColor = ta.getColor(R.styleable.Chip_mcv_iconTextColor, ContextCompat.getColor(context, R.color.colorChipCloseClicked))
        val iconTextBackgroundColor = ta.getColor(R.styleable.Chip_mcv_iconTextBackgroundColor, ContextCompat.getColor(context, R.color.colorChipBackgroundClicked))
        setIconText(iconText ?: "", iconTextColor, iconTextBackgroundColor)

        ta.recycle()

        if (selectable && closable) {
            throw IllegalStateException("Chip must be either selectable or closable. You set both true")
        }
    }

    private fun initDefaultValues() {
        chipBackgroundColor = ContextCompat.getColor(context, R.color.colorChipBackground)
        chipSelectedBackgroundColor = ContextCompat.getColor(context, R.color.colorChipBackgroundClicked)
        chipTextColor = ContextCompat.getColor(context, R.color.colorChipText)
        chipSelectedTextColor = ContextCompat.getColor(context, R.color.colorChipTextClicked)
        chipCloseColor = ContextCompat.getColor(context, R.color.colorChipCloseInactive)
        chipSelectedCloseColor = ContextCompat.getColor(context, R.color.colorChipCloseClicked)
        cornerRadius = resources.getDimensionPixelSize(R.dimen.chip_height) / 2
        val iconTextColor = ContextCompat.getColor(context, R.color.colorChipCloseClicked)
        val iconTextBackgroundColor = ContextCompat.getColor(context, R.color.colorChipBackgroundClicked)
        setIconText(iconText ?: "", iconTextColor, iconTextBackgroundColor)

        if (selectable && closable) {
            throw IllegalStateException("Chip must be either selectable or closable. You set both true")
        }
    }

    private fun buildView() {
        createPaddings()
        createChipText()
        createBackground()
        createChipIcons()
    }

    private fun createPaddings() {
        gravity = Gravity.CENTER
        val startPadding = if (chipIcon == null && chipIconBitmap == null && iconText == null) resources.getDimensionPixelSize(R.dimen.chip_icon_horizontal_margin) else 0
        val endPadding = if (selectable || closable) resources.getDimensionPixelSize(R.dimen.chip_close_horizontal_margin) else resources.getDimensionPixelSize(R.dimen.chip_icon_horizontal_margin)
        setPaddingRelative(startPadding, 0, if (chipSelectableWithoutIcon) startPadding else endPadding, 0)
        compoundDrawablePadding = resources.getDimensionPixelSize(R.dimen.chip_icon_horizontal_margin)
    }

    private fun createChipText() {
        setTextColor(if (chipSelected) chipSelectedTextColor else chipTextColor)
    }

    private fun createBackground() {
        val radius = cornerRadius.toFloat()
        val radii = floatArrayOf(radius, radius, radius, radius, radius, radius, radius, radius)
        var _strokeColor = strokeColor
        var _strokeSize = strokeSize
        var _chipBackgroundColor = chipBackgroundColor

        if (chipSelected) {
            _strokeColor = selectedStrokeColor
            _strokeSize = selectedStrokeSize
            _chipBackgroundColor = chipSelectedBackgroundColor
        }

        background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadii = radii
            setColor(_chipBackgroundColor)
            setStroke(_strokeSize, _strokeColor)
        }
    }

    private fun createChipIcons() {
        val closeIcon = createCloseIcon()
        val selectIcon = createSelectIcon()

        if (chipIcon == null && chipIconBitmap == null && iconText == null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, if (selectable) selectIcon else closeIcon, null)
            } else {
                setCompoundDrawablesWithIntrinsicBounds(null, null, if (selectable) selectIcon else closeIcon, null)
            }
            return
        }

        if (chipIcon != null && (chipIcon as BitmapDrawable).bitmap != null) {
            var bitmap = (chipIcon as BitmapDrawable).bitmap
            bitmap = ChipUtils.getSquareBitmap(bitmap)
            bitmap = ChipUtils.getScaledBitmap(context, bitmap)
            bitmap = ChipUtils.getCircleBitmap(context, bitmap, cornerRadius.toFloat())

            val drawable = BitmapDrawable(resources, bitmap)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, null, if (selectable) selectIcon else closeIcon, null)
            } else {
                setCompoundDrawablesWithIntrinsicBounds(drawable, null, if (selectable) selectIcon else closeIcon, null)
            }
        }

        if (chipIconBitmap != null) {
            chipIconBitmap = ChipUtils.getSquareBitmap(chipIconBitmap)
            chipIconBitmap = ChipUtils.getCircleBitmap(context, chipIconBitmap, cornerRadius.toFloat())

            val drawable = BitmapDrawable(resources, chipIconBitmap)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, null, if (selectable) selectIcon else closeIcon, null)
            } else {
                setCompoundDrawablesWithIntrinsicBounds(drawable, null, if (selectable) selectIcon else closeIcon, null)
            }
        }

        if (iconText != null && iconText != "") {
            val textBitmap = ChipUtils.getCircleBitmapWithText(context, iconText, iconTextColor, iconTextBackgroundColor, cornerRadius.toFloat())

            val drawable = BitmapDrawable(resources, textBitmap)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, null, if (selectable) selectIcon else closeIcon, null)
            } else {
                setCompoundDrawablesWithIntrinsicBounds(drawable, null, if (selectable) selectIcon else closeIcon, null)
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun createCloseIcon(): Drawable? {
        if (!closable) return null

        return ContextCompat.getDrawable(context, R.drawable.ic_close)?.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                mutate().colorFilter = BlendModeColorFilter(if (chipSelected) chipSelectedCloseColor else chipCloseColor, BlendMode.SRC_IN)
            } else {
                mutate().setColorFilter(if (chipSelected) chipSelectedCloseColor else chipCloseColor, PorterDuff.Mode.SRC_IN)
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun createSelectIcon(): Drawable? {
        if (!selectable || chipSelectableWithoutIcon) return null

        return ContextCompat.getDrawable(context, R.drawable.ic_select)?.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                mutate().colorFilter = BlendModeColorFilter(if (chipSelected) chipSelectedCloseColor else chipCloseColor, BlendMode.SRC_IN)
            } else {
                mutate().setColorFilter(if (chipSelected) chipSelectedCloseColor else chipCloseColor, PorterDuff.Mode.SRC_IN)
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measuredWidth, resources.getDimensionPixelSize(R.dimen.chip_height))
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) return super.onTouchEvent(event)

        var bounds: Rect

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                var positionX = event.x.toInt()
                var positionY = event.y.toInt()

                val right = compoundDrawables[2]

                right?.let { drawableRight ->
                    bounds = drawableRight.bounds

                    val extraClickingArea = (13 * resources.displayMetrics.density + 0.5).toInt()

                    positionX += extraClickingArea
                    positionY -= extraClickingArea

                    positionX = width - positionX
                    if (positionX <= 0) positionX += extraClickingArea
                    if (positionY <= 0) positionY = event.y.toInt()

                    if (bounds.contains(positionX, positionY)) {
                        if (closable) {
                            chipSelected = true
                            createBackground()
                            createChipText()
                            onCloseClickListener?.onCloseClick(this)
                        }
                        event.action = MotionEvent.ACTION_CANCEL
                        return true
                    }
                }
            }
            MotionEvent.ACTION_UP -> {
                var positionX = event.x.toInt()
                var positionY = event.y.toInt()

                val left = compoundDrawables[0]
                val right = compoundDrawables[2]

                left?.let { drawableLeft ->
                    bounds = drawableLeft.bounds

                    val extraClickArea = (13 * resources.displayMetrics.density + 0.5).toInt()

                    if (!bounds.contains(positionX, positionY)) {
                        positionX -= extraClickArea
                        positionY -= extraClickArea

                        if (positionX <= 0) positionX = event.x.toInt()
                        if (positionY <= 0) positionY = event.y.toInt()

                        if (positionX < positionY) positionY = positionX
                    } else {
                        onIconClickListener?.onIconClick(this)
                        event.action = MotionEvent.ACTION_CANCEL
                        return false
                    }
                }

                right?.let { drawableRight ->
                    bounds = drawableRight.bounds

                    val extraClickingArea = (13 * resources.displayMetrics.density + 0.5).toInt()

                    positionX += extraClickingArea
                    positionY -= extraClickingArea

                    positionX = width - positionX
                    if (positionX <= 0) positionX += extraClickingArea
                    if (positionY <= 0) positionY = event.y.toInt()

                    if (bounds.contains(positionX, positionY)) {
                        Log.e("Click", "Right")
                        if (selectable) {
                            chipSelected = !chipSelected
                            onSelectClickListener?.onSelectClick(this, chipSelected)
                        } else if (closable) {
                            chipSelected = false
                            onCloseClickListener?.onCloseClick(this)
                        }
                        buildView()
                        event.action = MotionEvent.ACTION_CANCEL
                        return false
                    }
                }
                if (closable) {
                    chipSelected = false
                    buildView()
                }
            }
        }

        return super.onTouchEvent(event)
    }

    fun setIconText(text: String, iconTextColor: Int = 0, iconTextBackgroundColor: Int = 0) {
        if (text == "") return

        this.iconText = ChipUtils.generateText(text)
        this.iconTextColor =
            if (iconTextColor == 0) ContextCompat.getColor(context, R.color.colorChipBackgroundClicked)
            else iconTextColor
        this.iconTextBackgroundColor =
            if (iconTextBackgroundColor == 0) ContextCompat.getColor(context, R.color.colorChipBackgroundClicked)
            else iconTextBackgroundColor
        buildView()
    }
}
