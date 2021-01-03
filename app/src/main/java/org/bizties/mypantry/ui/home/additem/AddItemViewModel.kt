package org.bizties.mypantry.ui.home.additem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.bizties.mypantry.repository.Category
import org.bizties.mypantry.repository.StockItemRepository

class AddItemViewModel(
    private val repo: StockItemRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    fun addItem(name: String, quantity: Float, categoryDescription: String, expiryDate: String?) {
        viewModelScope.launch(dispatcher) {
            val category = Category.fromDescription(categoryDescription)!!
            repo.addItem(name, quantity, category, expiryDate)
        }
    }
}