package org.bizties.mypantry.ui.dashboard.adapter

import androidx.recyclerview.widget.DiffUtil
import org.bizties.mypantry.ui.dashboard.model.InUseItem

class InUseListDiffUtils : DiffUtil.ItemCallback<InUseItem>() {

    override fun areItemsTheSame(oldItem: InUseItem, newItem: InUseItem): Boolean {
        return oldItem.stockItem.item.id == newItem.stockItem.item.id
    }

    override fun areContentsTheSame(oldItem: InUseItem, newItem: InUseItem): Boolean {
        return oldItem == newItem
    }
}