package org.bizties.mypantry.ui.home

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.bizties.mypantry.R

class SelectCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(category: String) {
        itemView.findViewById<TextView>(R.id.category_text_view).apply {
            text = category
        }
    }
}