package org.bizties.mypantry.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.bizties.mypantry.R
import org.bizties.mypantry.shared.getDrawableCompat
import org.bizties.mypantry.shared.gone
import org.bizties.mypantry.shared.visible
import org.bizties.mypantry.ui.dashboard.adapter.InUseItemSelectedListener
import org.bizties.mypantry.ui.dashboard.adapter.InUseListAdapter

class DashboardFragment : Fragment(), InUseItemSelectedListener {

    private lateinit var viewModel: DashboardViewModel
    private lateinit var listAdapter: InUseListAdapter
    private lateinit var finishButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this, OpenedItemsViewModelFactory()).get(DashboardViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        setHasOptionsMenu(true)

        val emptyStateTextView: TextView = root.findViewById(R.id.empty_state)
        finishButton = root.findViewById(R.id.finish_button)
        setupRecyclerView(root)

        finishButton.setOnClickListener {
            viewModel.deleteInUseItems(listAdapter.getAndClearSelectedItems())
            onNoneItemSelected()
        }

        viewModel.stockItems.observe(viewLifecycleOwner, Observer { listOfItems ->
            listAdapter.submitList(listOfItems)
        })

        viewModel.showEmptyState.observe(viewLifecycleOwner, Observer { show ->
            emptyStateTextView.visibility = if (show) View.VISIBLE else View.GONE
        })

        return root
    }

    private fun setupRecyclerView(root: View) {
        val recyclerView: RecyclerView = root.findViewById(R.id.recycler_view)

        listAdapter = InUseListAdapter(this)
        val manager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        with(recyclerView) {
            adapter = listAdapter
            layoutManager = manager
            val divider = DividerItemDecoration(context, manager.orientation)
            divider.setDrawable(context.getDrawableCompat(R.drawable.divider_stock_item)!!)
            addItemDecoration(divider)
        }
    }

    override fun onAnyItemSelected() {
        finishButton.visible()
    }

    override fun onNoneItemSelected() {
        finishButton.gone()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.opened_menu, menu)
    }

    private fun onFinishItemClick() {
        viewModel.enterEditMode()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.finish_item -> onFinishItemClick()
        }
        return super.onOptionsItemSelected(item)
    }
}