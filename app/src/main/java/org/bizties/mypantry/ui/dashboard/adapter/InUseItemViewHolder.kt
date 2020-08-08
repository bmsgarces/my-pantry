package org.bizties.mypantry.ui.dashboard.adapter

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.switchmaterial.SwitchMaterial
import org.bizties.mypantry.R
import org.bizties.mypantry.repository.StockItem
import org.bizties.mypantry.shared.gone
import org.bizties.mypantry.shared.printableQuantity
import org.bizties.mypantry.shared.visible
import org.bizties.mypantry.ui.dashboard.model.InUseItem

class InUseItemViewHolder(itemView: View, private var listener: InUseItemClickListener?) : RecyclerView.ViewHolder(itemView) {

    fun bind(inUseItem : InUseItem) {
        val nameTextView: TextView = itemView.findViewById(R.id.name)
        val quantityTextView: TextView = itemView.findViewById(R.id.quantity)
        val categoryTextView: TextView = itemView.findViewById(R.id.category)
        val expiryDateTextView: TextView = itemView.findViewById(R.id.expiry_date)
        // val dateOfOpeningTextView: TextView = itemView.findViewById(R.id.date_of_opening)
        val checkBox: CheckBox = itemView.findViewById(R.id.checkbox)

        val (stockItem, showCheckBox) = inUseItem
        val (item, quantities) = stockItem
        val quantity = quantities.first()

        nameTextView.text = item.name
        quantityTextView.text = quantity.amount.printableQuantity()
        categoryTextView.text = item.category.description
        expiryDateTextView.text = item.expiryDate

        if (!showCheckBox) {
            checkBox.gone()
            return
        }

        checkBox.visible()
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            listener?.onItemClick(item.id, isChecked)
        }

        // quantity.dateOfOpening?.let { date ->
        //     dateOfOpeningTextView.text = itemView.context.getString(R.string.date_of_opening, date)
        // }
        // dateOfOpeningTextView.goneIfEmpty()
    }

    // private fun TextView.goneIfEmpty() {
    //     if (text.isEmpty()) gone() else visible()
    // }
}