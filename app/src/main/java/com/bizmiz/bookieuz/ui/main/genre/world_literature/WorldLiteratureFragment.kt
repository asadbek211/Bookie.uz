package com.bizmiz.bookieuz.ui.main.genre.world_literature

import android.os.Bundle
import android.util.Log
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
import com.bizmiz.bookieuz.databinding.FragmentWorldLiteratureBinding
import com.bizmiz.bookieuz.ui.model.DataXX
import com.bizmiz.bookieuz.utils.ResourceState
import org.koin.androidx.viewmodel.ext.android.viewModel


class WorldLiteratureFragment : Fragment() {
    private val worldLiteratureViewModel: WorldLiteratureViewModel by viewModel()
    private lateinit var worldBookAdapter: WorldBookAdapter
    private var currentPage = 1
    private var nextPage:String? = null
    private lateinit var binding: FragmentWorldLiteratureBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        worldLiteratureViewModel.getCategoryDataByPage(1, 1)
        worldBookAdapter = WorldBookAdapter()
        binding = FragmentWorldLiteratureBinding.bind(
            inflater.inflate(
                R.layout.fragment_world_literature,
                container,
                false
            )
        )
        binding.worldBookRecView.adapter = worldBookAdapter
        worldBookAdapter.onClickListener {bookId->
            val bundle = bundleOf(
                "bookId" to bookId
            )
            val navController =
                Navigation.findNavController(requireActivity(), R.id.basicNavigation)
            navController.navigate(R.id.action_genre_to_viewBook,bundle)
        }
        binding.worldBookRecView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (nextPage!=null){
                        currentPage += 1
                        worldLiteratureViewModel.getCategoryDataByPage(1,currentPage )
                    }
                }
            }
        })
        categoryDataObserve()
        return binding.root
    }

    private fun categoryDataObserve() {
        worldLiteratureViewModel.categoryData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                ResourceState.SUCCESS -> {
                    currentPage = it.data?.current_page!!
                    nextPage = it.data.next_page_url
                    if (currentPage ==1){
                        worldBookAdapter.categoryList = (it.data.data as ArrayList<DataXX>?)!!
                    }else{
                        worldBookAdapter.categoryList.addAll(it.data.data)
                        worldBookAdapter.notifyDataSetChanged()
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