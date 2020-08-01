package org.bizties.mypantry.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import org.bizties.mypantry.repository.PantryItemDao

class DashboardViewModel(pantryItemDao: PantryItemDao) : ViewModel() {

    val pantryItems = pantryItemDao.getAllInUse()

    val showEmptyState: LiveData<Boolean> = Transformations.map(pantryItems) { list->
        list.isEmpty()
    }
}