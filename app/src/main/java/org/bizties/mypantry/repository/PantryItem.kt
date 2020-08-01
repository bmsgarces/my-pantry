package org.bizties.mypantry.repository

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class PantryItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "quantity") val quantity: Float,
    @ColumnInfo(name = "category") val category: Category,
    @ColumnInfo(name = "in_use") val inUse: Boolean,
    @ColumnInfo(name = "date_of_opening") val dataOfOpening: String? = null,
    @ColumnInfo(name = "expiry_date") val expiryDate: String? = null
) : Parcelable