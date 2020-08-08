package org.bizties.mypantry.ui.home.updateitem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.bizties.mypantry.MyPantryApplication

@Suppress("UNCHECKED_CAST")
class UpdateItemViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UpdateItemViewModel(MyPantryApplication.instance.getStockItemRepository()) as T
    }
}