package com.robertlevonyan.chip.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun PreviewMaterialChip() {
  Box(
    modifier = Modifier
      .padding(12.dp)
      .fillMaxSize()
  ) {
    MaterialChip(
      text = "Chip with compose",
      chipIcon = ChipIconRes(icon = R.drawable.ic_close),
      interaction = ChipInteraction.Selectable,
      strokeSize = 1.dp,
      strokeColor = Color.Red,
    )
  }
}
