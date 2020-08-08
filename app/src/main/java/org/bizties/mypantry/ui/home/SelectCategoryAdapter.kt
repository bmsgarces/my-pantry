package org.bizties.mypantry.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.bizties.mypantry.R

class SelectCategoryAdapter(
    private val listOfCategories: List<String>,
    private val listener: SelectCategoryListener
) : RecyclerView.Adapter<SelectCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectCategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SelectCategoryViewHolder(inflater.inflate(R.layout.view_category_item, parent, false))
    }

    override fun getItemCount(): Int = listOfCategories.size

    override fun onBindViewHolder(holder: SelectCategoryViewHolder, position: Int) {
        val category = listOfCategories[position]
        holder.bind(listOfCategories[position])
        holder.itemView.setOnClickListener {
            listener.onCategorySelected(category, position)
        }
    }
}