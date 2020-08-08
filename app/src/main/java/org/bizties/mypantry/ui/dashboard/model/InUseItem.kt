package org.bizties.mypantry.ui.dashboard.model

import org.bizties.mypantry.repository.StockItem

data class InUseItem(val stockItem: StockItem, val showCheckBox: Boolean)