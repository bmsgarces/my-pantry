package org.bizties.mypantry

import android.app.Application
import org.bizties.mypantry.repository.AppDatabase
import org.bizties.mypantry.repository.RoomTransactionRunner
import org.bizties.mypantry.repository.StockItemRepository

class MyPantryApplication : Application() {

    companion object {

        lateinit var instance: MyPantryApplication
    }

    private val database: AppDatabase by lazy {
        AppDatabase.newInstance(instance.applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getStockItemRepository(): StockItemRepository = StockItemRepository(
        transactionRunner = RoomTransactionRunner(database),
        pantryItemDao = database.pantryItemDao(),
        quantityDao = database.quantityDao(),
        stockItemDao = database.stockItemDao()
    )
}