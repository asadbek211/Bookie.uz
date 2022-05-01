package com.bizmiz.bookieuz.ui.main.home.latest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.bizmiz.bookieuz.R
import com.bizmiz.bookieuz.databinding.FragmentLatestBinding
import com.bizmiz.bookieuz.utils.ResourceState
import org.koin.androidx.viewmodel.ext.android.viewModel

class LatestFragment : Fragment() {
    private val latestViewModel:LatestViewModel by viewModel()
    private lateinit var binding: FragmentLatestBinding
    private lateinit var latestBookAdapter: LatestBookAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        latestViewModel.getLatest()
        latestBookAdapter = LatestBookAdapter()
        binding = FragmentLatestBinding.bind(inflater.inflate(R.layout.fragment_latest, container, false))
        binding.homeLatestRecView.adapter = latestBookAdapter
        latestBookAdapter.onClickListener {bookId->
            val bundle = bundleOf(
                "bookId" to bookId
            )
            val navController =
                Navigation.findNavController(requireActivity(), R.id.basicNavigation)
            navController.navigate(R.id.home_to_viewBook,bundle)
        }
        latestObserve()
        return binding.root
    }
    private fun latestObserve() {
        latestViewModel.latestList.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                ResourceState.SUCCESS -> {
                    if (it.data != null) {
                        binding.loading.visibility = View.GONE
                        latestBookAdapter.latestList = it.data
                    }
                }
                ResourceState.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}