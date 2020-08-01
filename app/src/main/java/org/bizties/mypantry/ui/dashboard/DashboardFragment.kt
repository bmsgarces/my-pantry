package org.bizties.mypantry.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.bizties.mypantry.R
import org.bizties.mypantry.ui.home.adapter.PantryListAdapter

class DashboardFragment : Fragment() {

    private lateinit var viewModel: DashboardViewModel
    private lateinit var listAdapter: PantryListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this, OpenedItemsViewModelFactory()).get(DashboardViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val emptyStateTextView: TextView = root.findViewById(R.id.empty_state)
        setupRecyclerView(root)

        viewModel.pantryItems.observe(viewLifecycleOwner, Observer { listOfItems ->
            listAdapter.submitList(listOfItems)
        })

        viewModel.showEmptyState.observe(viewLifecycleOwner, Observer { show ->
            emptyStateTextView.visibility = if (show) View.VISIBLE else View.GONE
        })

        return root
    }

    private fun setupRecyclerView(root: View) {
        val recyclerView: RecyclerView = root.findViewById(R.id.recycler_view)

        listAdapter = PantryListAdapter()
        val manager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        with(recyclerView) {
            adapter = listAdapter
            layoutManager = manager
        }
    }
}