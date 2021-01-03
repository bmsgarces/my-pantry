package org.bizties.mypantry.ui.home.updateitem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatSpinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.bizties.mypantry.R
import org.bizties.mypantry.repository.PantryItem
import org.bizties.mypantry.repository.StockItem

class UpdateItemFragment : Fragment() {

    companion object {
        const val STOCK_ITEM_BUNDLE_KEY = "stock_item"
    }

    private lateinit var viewModel: UpdateItemViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this,
            UpdateItemViewModelFactory()
        ).get(UpdateItemViewModel::class.java)

        return inflater.inflate(R.layout.fragment_update_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val spinner: AppCompatSpinner = view.findViewById(R.id.spinner)
        val nameTextView: TextView = view.findViewById(R.id.name_text_view)
        val button: Button = view.findViewById(R.id.update_item_button)

        val stockItem: StockItem? = requireArguments().getParcelable(STOCK_ITEM_BUNDLE_KEY)

        check(stockItem != null) { "StockItem not found" }

        val pantryItem: PantryItem = stockItem.item
        nameTextView.text = pantryItem.name

        ArrayAdapter<Int>(requireContext(), android.R.layout.simple_spinner_item).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            adapter.addAll(stockItem.getStockRange())
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        button.setOnClickListener {
            viewModel.startUsingQuantity(pantryItem.id, spinner.selectedItem as Int)
            onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onBackPressed() = requireActivity().onBackPressed()
}