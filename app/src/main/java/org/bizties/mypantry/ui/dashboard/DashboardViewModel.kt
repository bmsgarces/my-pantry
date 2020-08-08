package org.bizties.mypantry.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.bizties.mypantry.repository.StockItemRepository
import org.bizties.mypantry.shared.nullIfEmpty
import org.bizties.mypantry.ui.dashboard.model.InUseItem

class DashboardViewModel(private val repo: StockItemRepository) : ViewModel() {

    private val _stockItems = MediatorLiveData<List<InUseItem>>()

    val stockItems: LiveData<List<InUseItem>> = _stockItems

    val showEmptyState: LiveData<Boolean> = Transformations.map(stockItems) { list ->
        list.isEmpty()
    }

    init {
        _stockItems.addSource(repo.getAll()) { list ->
            _stockItems.value = list.mapNotNull { item ->
                val quantities = item.quantities.filter { it.type.isInUse() }.nullIfEmpty()
                quantities?.let { item.copy(quantities = it) }
            }
            .map { InUseItem(stockItem = it, showCheckBox = false) }
        }
    }

    fun enterEditMode() {
        val items = stockItems.value ?: return
        _stockItems.value = items.map { it.copy(showCheckBox = true) }
    }

    fun deleteInUseItems(list: List<Int>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repo.deleteInUseItems(list)
            }
        }
    }
}