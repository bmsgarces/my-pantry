package org.bizties.mypantry.ui.home.updateitem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatSpinner
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.bizties.mypantry.R
import org.bizties.mypantry.repository.PantryItem
import org.bizties.mypantry.repository.StockItem

class UpdateItemBottomSheet : BottomSheetDialogFragment() {

    companion object {

        private const val STOCK_ITEM_BUNDLE_KEY = "stock_item"

        fun newInstance(stockItem: StockItem): UpdateItemBottomSheet {
            val fragment = UpdateItemBottomSheet()
            fragment.arguments = Bundle().apply {
                putParcelable(STOCK_ITEM_BUNDLE_KEY, stockItem)
            }
            return fragment
        }
    }

    private lateinit var viewModel: UpdateItemViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_update_item, container, false)

        viewModel = ViewModelProvider(this,
            UpdateItemViewModelFactory()
        ).get(UpdateItemViewModel::class.java)

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
            dismiss()
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        setupBottomSheetBehavior()
    }

    private fun setupBottomSheetBehavior() {
        val bottomSheet = dialog?.findViewById(R.id.design_bottom_sheet) as? FrameLayout

        bottomSheet?.let { view ->
            val behavior = BottomSheetBehavior<FrameLayout>()
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.skipCollapsed = true

            (view.layoutParams as? CoordinatorLayout.LayoutParams)?.behavior = behavior
        }
    }
}