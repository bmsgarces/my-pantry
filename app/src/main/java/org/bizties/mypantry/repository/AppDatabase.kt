package org.bizties.mypantry.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [PantryItem::class, Quantity::class], version = 2, exportSchema = false)
@TypeConverters(RoomConverters::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        fun newInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "my-pantry-database")
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun pantryItemDao(): PantryItemDao

    abstract fun quantityDao(): QuantityDao

    abstract fun stockItemDao(): StockItemDao
}