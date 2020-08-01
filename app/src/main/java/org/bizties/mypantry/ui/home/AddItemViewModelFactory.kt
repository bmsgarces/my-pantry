package org.bizties.mypantry.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.bizties.mypantry.MyPantryApplication

@Suppress("UNCHECKED_CAST")
class AddItemViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddItemViewModel(MyPantryApplication.instance.getPantryItemDao()) as T
    }
}