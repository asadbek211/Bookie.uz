package com.bizmiz.bookieuz.ui.main.home.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.bizmiz.bookieuz.R
import com.bizmiz.bookieuz.databinding.FragmentViewsBinding
import com.bizmiz.bookieuz.ui.main.home.latest.LatestViewModel
import com.bizmiz.bookieuz.utils.ResourceState
import org.koin.androidx.viewmodel.ext.android.viewModel

class ViewsFragment : Fragment() {
    private lateinit var viewsBookAdapter: ViewsBookAdapter
    private val viewsBookViewModel:ViewsBookViewModel by viewModel()
    private lateinit var binding: FragmentViewsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewsBookViewModel.getViews()
        viewsBookAdapter = ViewsBookAdapter()
        binding =
            FragmentViewsBinding.bind(inflater.inflate(R.layout.fragment_views, container, false))
        binding.homeViewsRecView.adapter = viewsBookAdapter
        viewsBookAdapter.onClickListener {bookId->
            val bundle = bundleOf(
                "bookId" to bookId
            )
            val navController =
                Navigation.findNavController(requireActivity(), R.id.basicNavigation)
            navController.navigate(R.id.home_to_viewBook,bundle)
        }
        viewsBookObserve()
        return binding.root
    }
    private fun viewsBookObserve() {
        viewsBookViewModel.viewList.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                ResourceState.SUCCESS -> {
                    if (it.data != null) {
                        binding.loading.visibility = View.GONE
                        viewsBookAdapter.viewsList = it.data
                    }
                }
                ResourceState.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}