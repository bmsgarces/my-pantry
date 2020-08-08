package org.bizties.mypantry.repository

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StockItem(
    @Embedded val item: PantryItem,
    @Relation(
        parentColumn = "id",
        entityColumn = "item_id"
    )
    val quantities: List<Quantity>
) : Parcelable {

    @IgnoredOnParcel
    val inStockQuantity: Quantity?
        get() = quantities.firstOrNull { it.type.isInStock() }

    fun getStockRange(): List<Int> = (1..inStockQuantity!!.amount.toInt()).toList()
}