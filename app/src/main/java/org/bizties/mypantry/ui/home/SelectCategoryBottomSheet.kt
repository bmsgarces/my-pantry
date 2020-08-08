package org.bizties.mypantry.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.bizties.mypantry.R
import org.bizties.mypantry.repository.Category
import org.bizties.mypantry.shared.getDrawableCompat
import java.util.Locale

class SelectCategoryBottomSheet : BottomSheetDialogFragment(), SelectCategoryListener {

    companion object {
        const val REQUEST_KEY = "200"
        const val SELECTED_CATEGORY_BUNDLE_KEY = "selected_category"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_select_category, container, false)

        val recyclerView: RecyclerView = root.findViewById(R.id.category_recycler_view)

        with(recyclerView) {
            val manager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            val listOfCategories = Category.values().map { it.description.allCaps() }

            adapter = SelectCategoryAdapter(listOfCategories, this@SelectCategoryBottomSheet)
            layoutManager = manager

            val divider = DividerItemDecoration(context, manager.orientation)
            divider.setDrawable(context.getDrawableCompat(R.drawable.divider_stock_item)!!)
            addItemDecoration(divider)
        }

        return root
    }

    override fun onStart() {
        super.onStart()
        setupBottomSheetBehavior()
    }

    override fun onCategorySelected(category: String, position: Int) {
        parentFragmentManager.setFragmentResult(
            REQUEST_KEY,
            Bundle().apply { putString(SELECTED_CATEGORY_BUNDLE_KEY, category) }
        )
        dismiss()
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

    private fun String.allCaps(): String {
        return this.toUpperCase(Locale.getDefault())
    }
}