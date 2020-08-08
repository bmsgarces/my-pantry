package org.bizties.mypantry.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import org.bizties.mypantry.repository.StockItem
import org.bizties.mypantry.repository.StockItemRepository
import org.bizties.mypantry.shared.nullIfEmpty

class HomeViewModel(repo: StockItemRepository) : ViewModel() {

    val stockItems: LiveData<List<StockItem>> = Transformations.map(repo.getAll()) { list ->
        list.mapNotNull { item ->
            val quantities = item.quantities.filter { it.type.isInStock() }.nullIfEmpty()
            quantities?.let { item.copy(quantities = it) }
        }
    }

    val showEmptyState: LiveData<Boolean> = Transformations.map(stockItems) { list ->
        list.isEmpty()
    }
}