package org.bizties.mypantry.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import org.bizties.mypantry.R

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
        val categoryEditText: EditText = root.findViewById(R.id.category_edit_text)
        val expiryDateEditText: EditText = root.findViewById(R.id.expiry_date_edit_text)

        val button: Button = root.findViewById(R.id.add_item_button)
        button.setOnClickListener {
            viewModel.addItem(
                name = nameEditText.textToString(),
                quantity = quantityEditText.textToString().toFloat(),
                category = categoryEditText.textToString(),
                expiryDate = expiryDateEditText.textToString().nullIfEmpty()
            )
            dismiss()
        }

        return root
    }

    private fun String.nullIfEmpty(): String? = if (this.isEmpty()) null else this
    private fun EditText.textToString(): String = this.text.toString()
}