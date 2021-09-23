package com.robertlevonyan.chip.compose

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes

sealed class ChipIcon

class ChipIconRes(@DrawableRes val icon: Int) : ChipIcon()
class ChipIconBitmap(val icon: Bitmap) : ChipIcon()
class ChipIconDrawable(val icon: Drawable) : ChipIcon()
