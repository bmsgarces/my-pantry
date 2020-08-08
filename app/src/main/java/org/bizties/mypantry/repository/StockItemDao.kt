package org.bizties.mypantry.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface StockItemDao {

    @Transaction
    @Query("SELECT * FROM PantryItem")
    fun getAll(): LiveData<List<StockItem>>

    @Transaction
    @Query("SELECT * FROM PantryItem")
    fun getAllInStock(): LiveData<List<StockItem>>

    @Transaction
    @Query("SELECT * FROM PantryItem")
    fun getAllInUse(): LiveData<List<StockItem>>
}