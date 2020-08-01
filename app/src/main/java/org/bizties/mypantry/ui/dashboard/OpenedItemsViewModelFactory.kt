package org.bizties.mypantry.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.bizties.mypantry.MyPantryApplication

@Suppress("UNCHECKED_CAST")
class OpenedItemsViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DashboardViewModel(MyPantryApplication.instance.getPantryItemDao()) as T
    }
}