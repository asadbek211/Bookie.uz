package com.bizmiz.bookieuz.ui.main.genre.search

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bizmiz.bookieuz.R
import com.bizmiz.bookieuz.databinding.FragmentSearchBinding
import com.bizmiz.bookieuz.ui.main.genre.view_model.GenreViewModel
import com.bizmiz.bookieuz.ui.main.genre.world_literature.WorldBookAdapter
import com.bizmiz.bookieuz.utils.ResourceState
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {
    private val searchViewModel:SearchViewModel by viewModel()
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var binding: FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        searchAdapter = SearchAdapter()
        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireActivity(), R.color.white)
        binding = FragmentSearchBinding.bind(inflater.inflate(R.layout.fragment_search, container, false))
        binding.etSearch.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyboard(v)
                if (binding.etSearch.text.isNotEmpty()) {
                    val query = binding.etSearch.text.toString()
                  searchViewModel.getSearchData(query)
                } else {
                    binding.recView.visibility = View.GONE
                    binding.txtEslatma.text = "Qidiruv natijalari shu yerda ko'rinadi"
                }

                return@OnEditorActionListener true
            }
            false
        })
        observe()
        binding.recView.adapter = searchAdapter
        return binding.root
    }
    private fun hideKeyboard(view: View) {
        val inputMethodManager: InputMethodManager? =
            requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
    }
    private fun observe() {
        searchViewModel.searchData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                ResourceState.SUCCESS -> {
                    if (it.data.isNullOrEmpty()) {
                        binding.recView.visibility = View.GONE
                        binding.txtEslatma.text = "Natija topilmadi"
                    } else {
                        binding.recView.visibility = View.VISIBLE
                        searchAdapter.categoryList = it.data
                    }
                }
                ResourceState.ERROR -> {
                    binding.recView.visibility = View.GONE
                    Toast.makeText(requireActivity(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}