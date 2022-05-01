package com.bizmiz.bookieuz.ui.main.genre.karakalpak_folklor

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bizmiz.bookieuz.R
import com.bizmiz.bookieuz.databinding.FragmentKarakalpakFolkloreBinding
import com.bizmiz.bookieuz.ui.model.DataXX
import com.bizmiz.bookieuz.utils.ResourceState
import org.koin.androidx.viewmodel.ext.android.viewModel

class KarakalpakFolkloreFragment : Fragment() {
    private lateinit var folkSongsAdapter: FolkSongsAdapter
    private val karakalpakFalkloreViewModel:KarakalpakFalkloreViewModel by viewModel()
    private lateinit var binding: FragmentKarakalpakFolkloreBinding
    private var currentPage = 1
    private var total = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        karakalpakFalkloreViewModel.getCategoryDataByPage(5, 1)
        karakalpakFalkloreViewModel.getSubcategory()
        folkSongsAdapter = FolkSongsAdapter()
        binding = FragmentKarakalpakFolkloreBinding.bind(
            inflater.inflate(
                R.layout.fragment_karakalpak_folklore,
                container,
                false
            )
        )
        binding.falkloreRecView.adapter = folkSongsAdapter
        binding.falkloreRecView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (currentPage < total) {
                        currentPage += 1
                        karakalpakFalkloreViewModel.getCategoryDataByPage(5, currentPage)
                    }
                }
            }
        })
        folkSongsAdapter.onClickListener {bookId->
            val bundle = bundleOf(
                "bookId" to bookId
            )
            val navController =
                Navigation.findNavController(requireActivity(), R.id.basicNavigation)
            navController.navigate(R.id.action_genre_to_viewBook,bundle)
        }
        subcategoryObserve()
        categoryDataObserve()
        return binding.root
    }
    private fun subcategoryObserve() {
        karakalpakFalkloreViewModel.subcategoryData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                ResourceState.SUCCESS -> {
                    if (it!=null){
                        val adapter = ArrayAdapter(requireActivity(), R.layout.spinner_item, it.data!!)
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        binding.spCategory.adapter = adapter
                    }
                }
                ResourceState.ERROR -> {
                    Toast.makeText(requireActivity(), it.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }
    private fun categoryDataObserve() {
        karakalpakFalkloreViewModel.categoryData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                ResourceState.SUCCESS -> {
                    currentPage = it.data?.current_page!!
                    total = it.data.total
                    if (currentPage == 1) {
                        folkSongsAdapter.categoryList =
                            (it.data.data as ArrayList<DataXX>?)!!
                    } else {
                        folkSongsAdapter.categoryList.addAll(it.data.data)
                        folkSongsAdapter.notifyDataSetChanged()
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