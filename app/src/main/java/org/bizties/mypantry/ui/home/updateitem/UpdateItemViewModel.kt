package org.bizties.mypantry.ui.home.updateitem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.bizties.mypantry.repository.StockItemRepository

class UpdateItemViewModel(private val repo: StockItemRepository) : ViewModel() {

    fun startUsingQuantity(itemId: Int, inUseQty: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repo.startUsingQuantity(itemId, inUseQty)
            }
        }
    }
}