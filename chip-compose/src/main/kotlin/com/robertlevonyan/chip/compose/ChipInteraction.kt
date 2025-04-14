package com.robertlevonyan.chip.compose

sealed class ChipInteraction {
  data object Selectable: ChipInteraction()
  data object Closable: ChipInteraction()
  data object SelectableWithoutIcon: ChipInteraction()
  data object None: ChipInteraction()
}
