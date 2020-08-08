package org.bizties.mypantry.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface PantryItemDao {

    @Insert
    fun insert(item: PantryItem): Long

    @Insert
    fun insertAll(items: List<PantryItem>): LongArray

    @Update
    fun update(item: PantryItem)

    @Delete
    fun delete(item: PantryItem)
}