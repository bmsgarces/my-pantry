package org.bizties.mypantry.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.bizties.mypantry.R
import org.bizties.mypantry.repository.StockItem
import org.bizties.mypantry.shared.getDrawableCompat
import org.bizties.mypantry.ui.home.adapter.OnPantryItemClickListener
import org.bizties.mypantry.ui.home.adapter.PantryListAdapter
import org.bizties.mypantry.ui.home.additem.AddItemFragment
import org.bizties.mypantry.ui.home.updateitem.UpdateItemBottomSheet

class HomeFragment : Fragment(), OnPantryItemClickListener {

    private lateinit var viewModel: HomeViewModel
    private lateinit var listAdapter: PantryListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(
            this,
            HomeViewModelFactory()
        ).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        setHasOptionsMenu(true)

        val emptyStateTextView: TextView = root.findViewById(R.id.empty_state)
        setupRecyclerView(root)

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

        listAdapter = PantryListAdapter(this)
        val manager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        with(recyclerView) {
            adapter = listAdapter
            layoutManager = manager
            val divider = DividerItemDecoration(context, manager.orientation)
            divider.setDrawable(context.getDrawableCompat(R.drawable.divider_stock_item)!!)
            addItemDecoration(divider)
        }
    }

    override fun onItemClick(stockItem: StockItem) {
        showBottomSheet(stockItem)
    }

    private fun showBottomSheet(stockItem: StockItem) {
        val dialog = UpdateItemBottomSheet.newInstance(stockItem)
        dialog.show(parentFragmentManager, "update_stock_bottom_sheet")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.pantry_menu, menu)
    }

    private fun navigate() {
        AddItemFragment().show(parentFragmentManager, "AddItemFragment")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> navigate()
        }
        return super.onOptionsItemSelected(item)
    }
}