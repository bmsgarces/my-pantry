package org.bizties.mypantry.repository

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PantryItemDao {

    @Query("SELECT * FROM pantryitem WHERE in_use = 0 ORDER BY quantity ASC")
    fun getAllNotInUse(): LiveData<List<PantryItem>>

    @Query("SELECT * FROM pantryitem WHERE in_use = 1")
    fun getAllInUse(): LiveData<List<PantryItem>>

    @Insert
    fun insert(item: PantryItem)

    @Insert
    fun insertAll(items: List<PantryItem>)

    @Update
    fun update(item: PantryItem)

    @Delete
    fun delete(item: PantryItem)
}