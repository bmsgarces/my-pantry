package org.bizties.mypantry.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.bizties.mypantry.R
import org.bizties.mypantry.repository.PantryItem

class PantryListAdapter : ListAdapter<PantryItem, PantryItemViewHolder>(PantryListDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PantryItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PantryItemViewHolder(inflater.inflate(R.layout.view_pantry_item, parent, false))
    }

    override fun onBindViewHolder(holder: PantryItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}