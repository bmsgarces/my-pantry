package org.bizties.mypantry.ui.home.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.bizties.mypantry.R
import org.bizties.mypantry.repository.PantryItem

class PantryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(pantryItem : PantryItem) {
        val nameTextView: TextView = itemView.findViewById(R.id.name)
        val quantityTextView: TextView = itemView.findViewById(R.id.quantity)
        val categoryTextView: TextView = itemView.findViewById(R.id.category)
        val expiryDateTextView: TextView = itemView.findViewById(R.id.expiry_date)

        with(pantryItem) {
            nameTextView.text = name
            quantityTextView.text = quantity.printableQuantity()
            categoryTextView.text = category.description
            expiryDateTextView.text = expiryDate
        }
    }

    private fun Float.printableQuantity(): String {
        return if (this % 1 == 0f) this.toInt().toString() else this.toString()
    }
}