package com.robertlevonyan.views.chipsample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import com.robertlevonyan.chip.compose.ChipInteraction
import com.robertlevonyan.chip.compose.MaterialChip
import com.robertlevonyan.chip.compose.MaterialChipGroup

class ComposePreview {
  fun preview(activity: MainActivity) {
    val composableView = activity.findViewById<ComposeView>(R.id.composeView)

    composableView.setContent {
      Column(modifier = Modifier.fillMaxWidth()) {
        val isSelected = remember { mutableStateOf(false) }

        MaterialChip(
          text = "SELC",
          interaction = ChipInteraction.Selectable,
        )

        MaterialChipGroup(
          items = listOf("Chip1", "Chip2", "Chip3"),
          createTitle = { it },
          initialSelectedElementIndex = 1,
          interaction = ChipInteraction.Selectable,
          strokeSize = 1.dp,
          selectedStrokeSize = 2.dp,
          strokeColor = Color.Red,
          selectedStrokeColor = Color.Blue,
          backgroundColor = Color.Red,
          selectedBackgroundColor = Color.Blue,
        )

        Button(onClick = { isSelected.value = !isSelected.value }) {
          Text(text = "Select")
        }
      }
    }
  }
}
