package org.bizties.mypantry.ui.home.additem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.bizties.mypantry.R
import org.bizties.mypantry.ui.home.SelectCategoryBottomSheet

class AddItemFragment : Fragment() {

    private lateinit var viewModel: AddItemViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(
            this,
            AddItemViewModelFactory()
        ).get(AddItemViewModel::class.java)

        return inflater.inflate(R.layout.fragment_add_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val nameEditText: EditText = view.findViewById(R.id.name_edit_text)
        val quantityEditText: EditText = view.findViewById(R.id.quantity_edit_text)
        val expiryDateEditText = view.findViewById<EditText>(R.id.expiry_date_edit_text).also { editText ->
            editText.setOnFocusChangeListener { _, hasFocus -> editText.hint = if (hasFocus) "DD/MM/YY" else null }
        }

        val categoryEditText = view.findViewById<EditText>(R.id.category_edit_text).also { editText ->
            editText.keyListener = null
            editText.setOnClickListener { showSelectCategoryBottomSheet() }
            editText.setOnFocusChangeListener { _, hasFocus -> if (hasFocus) showSelectCategoryBottomSheet() }
        }

        val button: Button = view.findViewById(R.id.add_item_button)
        button.setOnClickListener {
            viewModel.addItem(
                name = nameEditText.textToString(),
                quantity = quantityEditText.textToString().toFloat(),
                categoryDescription = categoryEditText.textToString(),
                expiryDate = expiryDateEditText.textToString().nullIfEmpty()
            )
            requireActivity().onBackPressed()
        }

        parentFragmentManager.setFragmentResultListener(
            SelectCategoryBottomSheet.REQUEST_KEY,
            viewLifecycleOwner
        ) { _, result ->
            val category = result.getString(SelectCategoryBottomSheet.SELECTED_CATEGORY_BUNDLE_KEY)
            categoryEditText.setText(category)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> requireActivity().onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showSelectCategoryBottomSheet() {
        SelectCategoryBottomSheet().show(parentFragmentManager, "select_category_bottom_sheet")
    }

    private fun String.nullIfEmpty(): String? = if (this.isEmpty()) null else this
    private fun EditText.textToString(): String = this.text.toString()
}