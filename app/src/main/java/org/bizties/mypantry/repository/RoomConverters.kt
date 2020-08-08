package org.bizties.mypantry.repository

import androidx.room.TypeConverter

class RoomConverters {

    @TypeConverter
    fun categoryToString(category: Category) = category.toString()

    @TypeConverter
    fun stringToCategory(category: String?) = category?.let { Category.valueOf(it) }

    @TypeConverter
    fun stockTypeToString(stockType: StockType) = stockType.toString()

    @TypeConverter
    fun stringToStockType(stockType: String?) = stockType?.let { StockType.valueOf(it) }
}