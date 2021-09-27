package com.robertlevonyan.views.chipsample

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import com.robertlevonyan.chip.compose.ChipIconRes
import com.robertlevonyan.chip.compose.ChipInteraction
import com.robertlevonyan.chip.compose.MaterialChip

class ComposePreview {

  fun preview(activity: MainActivity) {
    val composableView = activity.findViewById<ComposeView>(R.id.composeView)

    composableView.setContent {
      Column(modifier = Modifier.fillMaxWidth()) {
        var isSelected by remember { mutableStateOf(false) }

        MaterialChip(
          text = "Some Text",
          chipIcon = ChipIconRes(icon = R.drawable.android),
          interaction = ChipInteraction.SelectableWithoutIcon,
          strokeSize = 1.dp,
          selectedStrokeSize = 2.dp,
          strokeColor = Color.Red,
          selectedStrokeColor = Color.Blue,
          initialSelected = isSelected,
          onSelectClick = { selected ->
            Toast.makeText(activity, "Sel $selected", Toast.LENGTH_SHORT).show()
          },
          onIconClick = {
            Toast.makeText(activity, "Icon", Toast.LENGTH_SHORT).show()
          },
          onChipClick = {
            Toast.makeText(activity, "Click", Toast.LENGTH_SHORT).show()
          },
          onCloseClick = {
            Toast.makeText(activity, "Close", Toast.LENGTH_SHORT).show()
          },
        )

        Button(onClick = { isSelected = !isSelected }) {
          Text(text = "Select")
        }
      }
    }
  }
}
