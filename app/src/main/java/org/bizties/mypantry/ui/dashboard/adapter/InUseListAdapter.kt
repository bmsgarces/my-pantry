package org.bizties.mypantry.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.bizties.mypantry.R
import org.bizties.mypantry.ui.dashboard.model.InUseItem
import java.util.Collections

class InUseListAdapter(
    private val listener: InUseItemSelectedListener
) : ListAdapter<InUseItem, InUseItemViewHolder>(InUseListDiffUtils()) {

    private val _selectedItems = mutableSetOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InUseItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val clickListener = object : InUseItemClickListener {
            override fun onItemClick(itemId: Int, isSelected: Boolean) {
                if (isSelected) _selectedItems.add(itemId) else _selectedItems.remove(itemId)
                when (_selectedItems.size) {
                    0 -> listener.onNoneItemSelected()
                    1 -> listener.onAnyItemSelected()
                }
            }
        }

        return InUseItemViewHolder(
            itemView = inflater.inflate(R.layout.view_pantry_item, parent, false),
            listener = clickListener
        )
    }

    override fun onBindViewHolder(holder: InUseItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    fun getAndClearSelectedItems(): List<Int> {
        val copyOfSelectedItems = _selectedItems.toList()
        clearSelectedItems()
        return copyOfSelectedItems
    }

    private fun clearSelectedItems() {
        _selectedItems.clear()
    }

    override fun onViewDetachedFromWindow(holder: InUseItemViewHolder) {
        super.onViewDetachedFromWindow(holder)
        // listener = null
    }
}