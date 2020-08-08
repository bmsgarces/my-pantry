package org.bizties.mypantry.ui.home.adapter

import androidx.recyclerview.widget.DiffUtil
import org.bizties.mypantry.repository.PantryItem
import org.bizties.mypantry.repository.StockItem

class PantryListDiffUtils : DiffUtil.ItemCallback<StockItem>() {

    override fun areItemsTheSame(oldItem: StockItem, newItem: StockItem): Boolean {
        return oldItem.item.id == newItem.item.id
    }

    override fun areContentsTheSame(oldItem: StockItem, newItem: StockItem): Boolean {
        return oldItem == newItem
    }
}