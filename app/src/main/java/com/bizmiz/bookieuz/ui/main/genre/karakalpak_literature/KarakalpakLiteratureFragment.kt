package com.bizmiz.bookieuz.ui.main.genre.karakalpak_literature

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
import com.bizmiz.bookieuz.databinding.FragmentKarakalpakLiteratureBinding
import com.bizmiz.bookieuz.ui.model.DataXX
import com.bizmiz.bookieuz.utils.ResourceState
import org.koin.androidx.viewmodel.ext.android.viewModel


class KarakalpakLiteratureFragment : Fragment() {
    private val karakalpakLiteratureViewModel: KarakalpakLiteratureViewModel by viewModel()
    private lateinit var karakalpakLiteratureAdapter: KarakalpakLiteratureAdapter
    private var currentPage = 1
    private var total = 0
    private lateinit var binding: FragmentKarakalpakLiteratureBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        karakalpakLiteratureViewModel.getCategoryDataByPage(3, 1)
        karakalpakLiteratureAdapter = KarakalpakLiteratureAdapter()
        binding = FragmentKarakalpakLiteratureBinding.bind(
            inflater.inflate(
                R.layout.fragment_karakalpak_literature,
                container,
                false
            )
        )
        binding.karakalpakLiteratureRecView.adapter = karakalpakLiteratureAdapter
        karakalpakLiteratureAdapter.onClickListener {bookId->
            val bundle = bundleOf(
                "bookId" to bookId
            )
            val navController =
                Navigation.findNavController(requireActivity(), R.id.basicNavigation)
            navController.navigate(R.id.action_genre_to_viewBook,bundle)
        }
        binding.karakalpakLiteratureRecView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (currentPage < total) {
                        currentPage += 1
                        karakalpakLiteratureViewModel.getCategoryDataByPage(3, currentPage)
                    }
                }
            }
        })
        categoryDataObserve()
        return binding.root
    }

    private fun categoryDataObserve() {
        karakalpakLiteratureViewModel.categoryData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                ResourceState.SUCCESS -> {
                    currentPage = it.data?.current_page!!
                    total = it.data.total
                    if (currentPage == 1) {
                        karakalpakLiteratureAdapter.categoryList =
                            (it.data.data as ArrayList<DataXX>?)!!
                    } else {
                        karakalpakLiteratureAdapter.categoryList.addAll(it.data.data)
                        karakalpakLiteratureAdapter.notifyDataSetChanged()
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