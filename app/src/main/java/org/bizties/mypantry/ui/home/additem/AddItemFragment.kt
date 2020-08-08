package org.bizties.mypantry.ui.home.additem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.ViewModelProvider
import org.bizties.mypantry.R
import org.bizties.mypantry.repository.Category
import org.bizties.mypantry.ui.home.SelectCategoryBottomSheet

class AddItemFragment : DialogFragment() {

    private lateinit var viewModel: AddItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(
            this,
            AddItemViewModelFactory()
        ).get(AddItemViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_add_item, container, false)

        val nameEditText: EditText = root.findViewById(R.id.name_edit_text)
        val quantityEditText: EditText = root.findViewById(R.id.quantity_edit_text)
        val expiryDateEditText: EditText = root.findViewById(R.id.expiry_date_edit_text)
        val categoryEditText = root.findViewById<EditText>(R.id.category_edit_text).also { editText ->
            editText.keyListener = null
            editText.setOnClickListener { showSelectCategoryBottomSheet() }
        }

        val button: Button = root.findViewById(R.id.add_item_button)
        button.setOnClickListener {
            val category = Category.fromDescription(categoryEditText.textToString())
            viewModel.addItem(
                name = nameEditText.textToString(),
                quantity = quantityEditText.textToString().toFloat(),
                category = category!!.description,
                expiryDate = expiryDateEditText.textToString().nullIfEmpty()
            )
            dismiss()
        }

        parentFragmentManager.setFragmentResultListener(
            SelectCategoryBottomSheet.REQUEST_KEY,
            viewLifecycleOwner,
            FragmentResultListener { _, result ->
                val category = result.getString(SelectCategoryBottomSheet.SELECTED_CATEGORY_BUNDLE_KEY)
                categoryEditText.setText(category)
            }
        )

        return root
    }

    private fun showSelectCategoryBottomSheet() {
        val dialog = SelectCategoryBottomSheet()
        dialog.show(parentFragmentManager, "select_category_bottom_sheet")
    }

    private fun String.nullIfEmpty(): String? = if (this.isEmpty()) null else this
    private fun EditText.textToString(): String = this.text.toString()
}