package org.bizties.mypantry.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuantityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(quantity: Quantity)

    @Delete
    fun delete(quantity: Quantity)

    @Query("SELECT * FROM Quantity WHERE item_id = :itemId AND stock_type LIKE 'in_stock'")
    fun getInStockByItemId(itemId: Int): Quantity

    @Query("SELECT * FROM Quantity WHERE item_id = :itemId AND stock_type LIKE 'in_use'")
    fun getInUseByItemId(itemId: Int): Quantity?

    @Query("DELETE FROM QUANTITY WHERE item_id IN (:list) AND stock_type LIKE 'in_use'")
    fun deleteAllInUse(list: List<Int>)
}