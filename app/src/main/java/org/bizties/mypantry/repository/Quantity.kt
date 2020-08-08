package org.bizties.mypantry.repository

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Entity(primaryKeys = ["item_id", "stock_type"])
@Parcelize
data class Quantity(
    @ColumnInfo(name = "item_id") val itemId: Int,
    @ColumnInfo(name = "stock_type") val type: StockType,
    @ColumnInfo(name = "amount") val amount: Float,
    @ColumnInfo(name = "date_of_opening") val dateOfOpening: String? = null
) : Parcelable