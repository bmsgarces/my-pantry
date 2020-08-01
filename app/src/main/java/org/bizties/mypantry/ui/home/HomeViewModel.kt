package org.bizties.mypantry.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import org.bizties.mypantry.repository.PantryItem
import org.bizties.mypantry.repository.PantryItemDao

class HomeViewModel(pantryItemDao: PantryItemDao) : ViewModel() {

    val pantryItems: LiveData<List<PantryItem>> = pantryItemDao.getAllNotInUse()

    val showEmptyState: LiveData<Boolean> = Transformations.map(pantryItems) { list ->
        list.isEmpty()
    }
}