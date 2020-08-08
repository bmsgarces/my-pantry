package org.bizties.mypantry.ui.home.adapter

import org.bizties.mypantry.repository.StockItem

interface OnPantryItemClickListener {
    fun onItemClick(stockItem: StockItem)
}