package com.robertlevonyan.chip.compose

import android.graphics.drawable.BitmapDrawable
import android.view.MotionEvent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private val CHIP_HEIGHT = 32.dp
private val ICON_SIZE = 28.dp
private val ICON_PADDING = 4.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MaterialChip(
  text: String,
  modifier: Modifier = Modifier,
  chipIcon: ChipIcon? = null,
  interaction: ChipInteraction = ChipInteraction.None,
  selected: MutableState<Boolean> = mutableStateOf(false),
  backgroundColor: Color = colorResource(id = R.color.colorChipBackground),
  selectedBackgroundColor: Color = colorResource(id = R.color.colorChipBackgroundSelected),
  textColor: Color = colorResource(id = R.color.colorChipText),
  selectedTextColor: Color = colorResource(id = R.color.colorChipTextSelected),
  closeIconColor: Color = colorResource(id = R.color.colorChipClose),
  selectedCloseIconColor: Color = colorResource(id = R.color.colorChipCloseSelected),
  cornerRadius: Dp = 16.dp,
  strokeSize: Dp = 0.dp,
  selectedStrokeSize: Dp = 0.dp,
  strokeColor: Color = Color.Transparent,
  selectedStrokeColor: Color = Color.Transparent,
  horizontalPadding: Dp = 8.dp,
  fontFamily: FontFamily? = null,
  onChipClick: () -> Unit = {},
  onCloseClick: () -> Unit = {},
  onSelectClick: (Boolean) -> Unit = {},
  onIconClick: () -> Unit = {},
) {
  var isSelected by selected

  val chipShape = RoundedCornerShape(cornerRadius)

  val chipBackgroundColor = if (isSelected) selectedBackgroundColor else backgroundColor
  val chipTextColor = if (isSelected) selectedTextColor else textColor
  val chipStrokeSize = if (isSelected) selectedStrokeSize else strokeSize
  val chipStrokeColor = if (isSelected) selectedStrokeColor else strokeColor
  val chipCloseIconColor = if (isSelected) selectedCloseIconColor else closeIconColor

  Surface(
    shape = chipShape,
    modifier = modifier
      .padding(end = 8.dp)
      .height(CHIP_HEIGHT)
      .wrapContentWidth()
      .apply {
        padding(0.dp)
        if (chipIcon == null) {
          padding(horizontal = horizontalPadding)
        } else {
          padding(end = horizontalPadding)
        }
      }
      .border(width = chipStrokeSize, color = chipStrokeColor, shape = chipShape),
    color = chipBackgroundColor,
  ) {
    Row(
      modifier = modifier
        .height(CHIP_HEIGHT)
        .wrapContentWidth(),
    ) {
      if (chipIcon != null) {
        val chipPainter = when (chipIcon) {
          is ChipIconBitmap -> BitmapPainter(image = chipIcon.icon.asImageBitmap())
          is ChipIconDrawable -> {
            val bmp = (chipIcon.icon as BitmapDrawable).bitmap
            BitmapPainter(image = bmp.asImageBitmap())
          }
          is ChipIconRes -> painterResource(id = chipIcon.icon)
        }
        Image(
          painter = chipPainter,
          modifier = Modifier
            .height(CHIP_HEIGHT)
            .width(CHIP_HEIGHT)
            .size(CHIP_HEIGHT)
            .clip(CircleShape)
            .clickable { onIconClick.invoke() },
          contentScale = ContentScale.Crop,
          contentDescription = "",
        )
      }

      Text(
        text = text,
        modifier = Modifier
          .wrapContentHeight()
          .wrapContentWidth()
          .align(Alignment.CenterVertically)
          .padding(horizontal = horizontalPadding)
          .clickable {
            if (interaction == ChipInteraction.SelectableWithoutIcon) {
              isSelected = !isSelected
              onSelectClick.invoke(isSelected)
            } else {
              onChipClick.invoke()
            }
          },
        textAlign = TextAlign.Center,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = chipTextColor,
        fontFamily = fontFamily,
      )

      when (interaction) {
        ChipInteraction.Closable -> {
          ClosableImageIcon(
            chipCloseIconColor = chipCloseIconColor,
            onPressed = { isSelected = true },
            onReleased = {
              isSelected = false
              onCloseClick.invoke()
            },
          )
        }
        ChipInteraction.Selectable -> {
          SelectableImageIcon(chipCloseIconColor = chipCloseIconColor) {
            isSelected = !isSelected
            onSelectClick.invoke(isSelected)
          }
        }
        else -> {
        }
      }
    }
  }
}

@ExperimentalComposeUiApi
@Composable
private fun RowScope.ClosableImageIcon(chipCloseIconColor: Color, onPressed: () -> Unit, onReleased: () -> Unit) {
  Image(
    painter = painterResource(id = R.drawable.ic_close),
    modifier = Modifier
      .height(ICON_SIZE)
      .width(ICON_SIZE)
      .padding(end = ICON_PADDING)
      .align(Alignment.CenterVertically)
      .pointerInteropFilter { motionEvent ->
        when (motionEvent.action) {
          MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> onPressed.invoke()
          MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> onReleased.invoke()
          MotionEvent.ACTION_HOVER_ENTER, MotionEvent.ACTION_HOVER_EXIT, MotionEvent.ACTION_HOVER_MOVE -> Unit
        }
        true
      },
    colorFilter = ColorFilter.tint(chipCloseIconColor),
    contentDescription = "",
  )
}

@Composable
private fun RowScope.SelectableImageIcon(chipCloseIconColor: Color, onTap: () -> Unit) {
  Image(
    painter = painterResource(id = R.drawable.ic_select),
    modifier = Modifier
      .height(ICON_SIZE)
      .width(ICON_SIZE)
      .padding(end = ICON_PADDING)
      .align(Alignment.CenterVertically)
      .pointerInput(Unit) {
        detectTapGestures(
          onTap = { onTap.invoke() },
        )
      },
    contentScale = ContentScale.Inside,
    colorFilter = ColorFilter.tint(chipCloseIconColor),
    contentDescription = "",
  )
}
