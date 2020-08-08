package org.bizties.mypantry.ui.home.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.bizties.mypantry.R
import org.bizties.mypantry.repository.StockItem
import org.bizties.mypantry.shared.printableQuantity

class PantryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(stockItem : StockItem) {
        val nameTextView: TextView = itemView.findViewById(R.id.name)
        val quantityTextView: TextView = itemView.findViewById(R.id.quantity)
        val categoryTextView: TextView = itemView.findViewById(R.id.category)
        val expiryDateTextView: TextView = itemView.findViewById(R.id.expiry_date)

        val (item, quantities) = stockItem
        val quantity = quantities.first()

        nameTextView.text = item.name
        quantityTextView.text = quantity.amount.printableQuantity()
        categoryTextView.text = item.category.description
        expiryDateTextView.text = item.expiryDate
    }
}