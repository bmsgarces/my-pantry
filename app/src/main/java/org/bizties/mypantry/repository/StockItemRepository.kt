package org.bizties.mypantry.repository

import androidx.lifecycle.LiveData
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class StockItemRepository(
    private val transactionRunner: DatabaseTransactionRunner,
    private val pantryItemDao: PantryItemDao,
    private val quantityDao: QuantityDao,
    private val stockItemDao: StockItemDao
) {

    fun addItem(name: String, quantity: Float, category: String, expiryDate: String?) {
        transactionRunner.runInTransaction {
            val id = pantryItemDao.insert(
                PantryItem(name = name, category = Category.valueOf(category), expiryDate = expiryDate)
            )
            quantityDao.insert(Quantity(itemId = id.toInt(), type = StockType.IN_STOCK, amount = quantity))
        }
    }

    fun getAll(): LiveData<List<StockItem>> = stockItemDao.getAll()

    private fun Calendar.now(): String {
        val formatter = SimpleDateFormat("dd-MM-yy", Locale.getDefault())
        return formatter.format(this.time)
    }

    private fun updateInStockQuantity(itemId: Int, inUseQty: Int) {
        val cachedInStockQty = quantityDao.getInStockByItemId(itemId)
        val inStockAmount = cachedInStockQty.amount - inUseQty

        if (inStockAmount == 0f) {
            quantityDao.delete(cachedInStockQty)
        } else {
            quantityDao.insert(cachedInStockQty.copy(amount = inStockAmount))
        }
    }

    private fun updateInUseQuantity(itemId: Int, inUseQty: Int) {
        val cachedInUseQty = quantityDao.getInUseByItemId(itemId)
        val dateOfOpening = Calendar.getInstance().now()

        val newInUseQty = cachedInUseQty?.copy(amount = inUseQty + cachedInUseQty.amount)
            ?: Quantity(
                itemId = itemId,
                type = StockType.IN_USE,
                amount = inUseQty.toFloat(),
                dateOfOpening = dateOfOpening
            )

        quantityDao.insert(newInUseQty)
    }

    fun startUsingQuantity(itemId: Int, inUseQty: Int) {
        transactionRunner.runInTransaction {
            // first update in_stock quantity
            updateInStockQuantity(itemId, inUseQty)
            // then update in_use quantity
            updateInUseQuantity(itemId, inUseQty)
        }
    }

    fun deleteInUseItems(list: List<Int>) {
        transactionRunner.runInTransaction {
            quantityDao.deleteAllInUse(list)
        }
    }
}