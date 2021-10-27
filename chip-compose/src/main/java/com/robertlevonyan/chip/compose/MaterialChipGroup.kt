package com.robertlevonyan.chip.compose

import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun <T> MaterialChipGroup(
  modifier: Modifier = Modifier,
  state: LazyListState = rememberLazyListState(),
  contentPadding: PaddingValues = PaddingValues(4.dp),
  verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
  flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
  initialSelectedElementIndex: Int = -1,
  items: List<T> = emptyList(),
  interaction: ChipInteraction = ChipInteraction.None,
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
  createTitle: (T) -> String,
  onItemSelected: (Int, T) -> Unit = { _, _ -> },
) {
  var allItems: List<T> by remember { mutableStateOf(items) }
  var selectedIndex: Int by remember { mutableStateOf(initialSelectedElementIndex) }

  LazyRow(
    modifier = modifier,
    state = state,
    contentPadding = contentPadding,
    verticalAlignment = verticalAlignment,
    flingBehavior = flingBehavior,
    reverseLayout = false,
    content = {
      itemsIndexed(allItems) { index, item ->
        MaterialChip(
          modifier = Modifier,
          text = createTitle(item),
          interaction = interaction,
          backgroundColor = backgroundColor,
          selectedBackgroundColor = selectedBackgroundColor,
          textColor = textColor,
          selectedTextColor = selectedTextColor,
          closeIconColor = closeIconColor,
          selectedCloseIconColor = selectedCloseIconColor,
          cornerRadius = cornerRadius,
          strokeSize = strokeSize,
          selectedStrokeSize = selectedStrokeSize,
          strokeColor = strokeColor,
          selectedStrokeColor = selectedStrokeColor,
          horizontalPadding = horizontalPadding,
          fontFamily = fontFamily,
          selected = mutableStateOf(selectedIndex == index).also { it.value },
          onChipClick = {
            if (selectedIndex != index) {
              onItemSelected(index, item)
            }
          },
          onCloseClick = {
            if (interaction == ChipInteraction.Closable) {
              allItems = allItems.toMutableList().apply { removeAt(index) }
            }
          },
          onSelectClick = { selected ->
            if (selectedIndex != index) {
              if (selected) {
                selectedIndex = index
              }
              if (interaction == ChipInteraction.SelectableWithoutIcon) {
                onItemSelected(index, item)
              }
            }
          },
        )
      }
    }
  )
}