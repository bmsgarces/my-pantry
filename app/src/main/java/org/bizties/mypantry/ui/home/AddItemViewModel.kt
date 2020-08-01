package org.bizties.mypantry.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.bizties.mypantry.repository.Category
import org.bizties.mypantry.repository.PantryItem
import org.bizties.mypantry.repository.PantryItemDao
import java.util.Locale

class AddItemViewModel(private val pantryItemDao: PantryItemDao) : ViewModel() {

    fun addItem(name: String, quantity: Float, category: String, expiryDate: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            pantryItemDao.insert(
                PantryItem(
                    name = name,
                    quantity = quantity,
                    category = Category.valueOf(category.toUpperCase(Locale.getDefault())),
                    inUse = false,
                    expiryDate = expiryDate
                )
            )
        }
    }
}