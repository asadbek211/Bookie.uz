package com.bizmiz.bookieuz.ui.main.genre.short_audios

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bizmiz.bookieuz.R
import com.bizmiz.bookieuz.databinding.FragmentShortAudiosBinding
import com.bizmiz.bookieuz.ui.main.genre.uzbek_literature.UzbBookAdapter
import com.bizmiz.bookieuz.ui.main.genre.uzbek_literature.UzbekLiteratureViewModel
import com.bizmiz.bookieuz.ui.model.DataXX
import com.bizmiz.bookieuz.utils.ResourceState
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShortAudiosFragment : Fragment() {
    private val shortAudiosViewModel:ShortAudiosViewModel by viewModel()
    private var currentPage = 1
    private var nextPage:String? = null
    private lateinit var shortAudiosAdapter: ShortAudiosAdapter
    private lateinit var binding: FragmentShortAudiosBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        shortAudiosViewModel.getCategoryDataByPage(4, 1)
        shortAudiosAdapter = ShortAudiosAdapter()
        binding = FragmentShortAudiosBinding.bind(inflater.inflate(R.layout.fragment_short_audios, container, false))
        binding.shortBookRecView.adapter = shortAudiosAdapter
        binding.shortBookRecView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (nextPage!=null) {
                        currentPage += 1
                        shortAudiosViewModel.getCategoryDataByPage(4, currentPage)
                    }
                }
            }
        })
        shortAudiosAdapter.onClickListener {bookId->
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
        shortAudiosViewModel.categoryData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                ResourceState.SUCCESS -> {
                    currentPage = it.data?.current_page!!
                    nextPage = it.data.next_page_url
                    if (currentPage == 1) {
                        shortAudiosAdapter.categoryList =
                            (it.data.data as ArrayList<DataXX>?)!!
                    } else {
                        shortAudiosAdapter.categoryList.addAll(it.data.data)
                        shortAudiosAdapter.notifyDataSetChanged()
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