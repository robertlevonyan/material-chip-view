package com.robertlevonyan.views.chipsample

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
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
      Box(modifier = Modifier.fillMaxWidth()) {
        MaterialChip(
          text = "Some Text",
          chipIcon = ChipIconRes(icon = R.drawable.android),
          interaction = ChipInteraction.Closable,
          strokeSize = 1.dp,
          selectedStrokeSize = 2.dp,
          strokeColor = Color.Red,
          selectedStrokeColor = Color.Blue,
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
          }
        )
      }
    }
  }
}
