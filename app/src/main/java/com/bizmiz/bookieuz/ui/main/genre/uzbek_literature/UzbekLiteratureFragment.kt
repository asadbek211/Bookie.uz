package com.bizmiz.bookieuz.ui.main.genre.uzbek_literature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bizmiz.bookieuz.R
import com.bizmiz.bookieuz.databinding.FragmentUzbekLiteratureBinding
import com.bizmiz.bookieuz.ui.model.DataXX
import com.bizmiz.bookieuz.utils.ResourceState
import org.koin.androidx.viewmodel.ext.android.viewModel

class UzbekLiteratureFragment : Fragment() {
    private val uzbekLiteratureViewModel: UzbekLiteratureViewModel by viewModel()
    private lateinit var uzbBookAdapter: UzbBookAdapter
    private var currentPage = 1
    private var nextPage:String? = null
    private lateinit var binding: FragmentUzbekLiteratureBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        uzbekLiteratureViewModel.getCategoryDataByPage(2, 1)
        uzbBookAdapter = UzbBookAdapter()
        binding = FragmentUzbekLiteratureBinding.bind(
            inflater.inflate(
                R.layout.fragment_uzbek_literature,
                container,
                false
            )
        )
        binding.uzbBookRecView.adapter = uzbBookAdapter
        binding.uzbBookRecView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (nextPage!=null) {
                        currentPage += 1
                        uzbekLiteratureViewModel.getCategoryDataByPage(2, currentPage)
                    }
                }
            }
        })
        uzbBookAdapter.onClickListener {bookId->
            val bundle = bundleOf(
                "bookId" to bookId
            )
            val navController =
                Navigation.findNavController(requireActivity(), R.id.basicNavigation)
            navController.navigate(R.id.action_genre_to_viewBook,bundle)
        }
        categoryDataObserve()
        return binding.root
    }
    private fun categoryDataObserve() {
        uzbekLiteratureViewModel.categoryData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                ResourceState.SUCCESS -> {
                    currentPage = it.data?.current_page!!
                    nextPage = it.data.next_page_url
                    if (currentPage == 1) {
                        uzbBookAdapter.categoryList =
                            (it.data.data as ArrayList<DataXX>?)!!
                    } else {
                        uzbBookAdapter.categoryList.addAll(it.data.data)
                        uzbBookAdapter.notifyDataSetChanged()
                    }

                }
                ResourceState.ERROR -> {
                    Toast.makeText(requireActivity(), it.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }
}