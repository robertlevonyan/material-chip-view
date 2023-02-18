package com.robertlevonyan.chip.compose

sealed class ChipInteraction {
  object Selectable: ChipInteraction()
  object Closable: ChipInteraction()
  object SelectableWithoutIcon: ChipInteraction()
  object None: ChipInteraction()
}
