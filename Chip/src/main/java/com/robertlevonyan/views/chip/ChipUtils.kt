package com.robertlevonyan.views.chip

import android.graphics.*
import java.util.*

internal fun Bitmap.getScaledBitmap(size: Int): Bitmap = Bitmap.createScaledBitmap(this, size, size, false)

internal fun Bitmap.getSquareBitmap(): Bitmap = if (width >= height) {
  Bitmap.createBitmap(this, width / 2 - height / 2, 0, height, height)
} else {
  Bitmap.createBitmap(this, 0, height / 2 - width / 2, width, width)
}

internal fun Bitmap.getCircleBitmap(size: Int, radius: Float): Bitmap {
  val output = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
  val canvas = Canvas(output)

  val color = Color.RED
  val paint = Paint()
  val rect = Rect(0, 0, size, size)
  val rectF = RectF(rect)

  paint.isAntiAlias = true
  canvas.drawARGB(0, 0, 0, 0)
  paint.color = color
  canvas.drawRoundRect(rectF, radius, radius, paint)

  paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
  canvas.drawBitmap(this, rect, rect, paint)

  return output
}

internal fun getCircleBitmapWithText(size: Int, text: String, textColor: Int, bgColor: Int, radius: Float): Bitmap {
  val output = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
  val canvas = Canvas(output)

  val paint = Paint()
  val textPaint = Paint()
  val rect = Rect(0, 0, size, size)
  val rectF = RectF(rect)

  paint.isAntiAlias = true
  canvas.drawARGB(0, 0, 0, 0)
  paint.color = bgColor
  canvas.drawRoundRect(rectF, radius, radius, paint)
  textPaint.color = textColor
  textPaint.strokeWidth = 30f
  textPaint.textSize = 45f
  paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OVER)
  textPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP)

  val (xPos, yPos) =
    if (text.length == 1) {
      (canvas.width / 2) + ((textPaint.descent() + textPaint.ascent()) / 2) to
          (canvas.height / 2) - ((textPaint.descent() + textPaint.ascent()) / 2)
    } else {
      (canvas.width / 3) + ((textPaint.descent() + textPaint.ascent()) / 2) to
          (canvas.height / 2) - ((textPaint.descent() + textPaint.ascent()) / 2)
    }

  canvas.drawBitmap(output, rect, rect, paint)
  canvas.drawText(text, xPos, yPos, textPaint)

  return output
}

internal fun String.generateText(): String {
  if (this.isEmpty()) {
    throw IllegalStateException("Icon text must have at least one symbol")
  }
  if (this.length == 1 || this.length == 2) {
    return this
  }

  val parts = this.split(" ")
  if (parts.size == 1) {
    val text = parts[0].substring(0, 2)

    val f = text.substring(0, 1).uppercase(Locale.getDefault())
    val s = text.substring(1, 2).lowercase(Locale.getDefault())

    return "$f$s"
  }

  val first = parts[0].substring(0, 1).uppercase(Locale.getDefault())
  val second = parts[1].substring(0, 1).uppercase(Locale.getDefault())

  return "$first$second"
}
