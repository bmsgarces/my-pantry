package org.bizties.mypantry.ui.home.adapter

import androidx.recyclerview.widget.DiffUtil
import org.bizties.mypantry.repository.PantryItem

class PantryListDiffUtils : DiffUtil.ItemCallback<PantryItem>() {

    override fun areItemsTheSame(oldItem: PantryItem, newItem: PantryItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PantryItem, newItem: PantryItem): Boolean {
        return oldItem == newItem
    }
}