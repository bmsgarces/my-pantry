package org.bizties.mypantry.ui.home.additem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.bizties.mypantry.repository.StockItemRepository

class AddItemViewModel(private val repo: StockItemRepository) : ViewModel() {

    fun addItem(name: String, quantity: Float, category: String, expiryDate: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addItem(name, quantity, category, expiryDate)
        }
    }
}