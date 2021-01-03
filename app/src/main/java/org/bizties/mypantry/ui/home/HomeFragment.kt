package org.bizties.mypantry.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.bizties.mypantry.R
import org.bizties.mypantry.repository.StockItem
import org.bizties.mypantry.shared.getDrawableCompat
import org.bizties.mypantry.ui.home.adapter.OnPantryItemClickListener
import org.bizties.mypantry.ui.home.adapter.PantryListAdapter
import org.bizties.mypantry.ui.home.updateitem.UpdateItemBottomSheet

class HomeFragment : Fragment(), OnPantryItemClickListener {

    private lateinit var viewModel: HomeViewModel
    private lateinit var listAdapter: PantryListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(
            this,
            HomeViewModelFactory()
        ).get(HomeViewModel::class.java)

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emptyStateTextView: TextView = view.findViewById(R.id.empty_state)
        setupRecyclerView(view)

        val fab: FloatingActionButton = view.findViewById(R.id.fab)
        fab.setOnClickListener { navigate() }

        viewModel.stockItems.observe(viewLifecycleOwner) { listOfItems ->
            listAdapter.submitList(listOfItems)
        }

        viewModel.showEmptyState.observe(viewLifecycleOwner) { show ->
            emptyStateTextView.visibility = if (show) View.VISIBLE else View.GONE
        }
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

    private fun navigate() {
        findNavController().navigate(R.id.action_homeFragment_to_addItemFragment)
    }
}