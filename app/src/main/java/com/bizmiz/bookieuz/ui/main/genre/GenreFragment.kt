package com.bizmiz.bookieuz.ui.main.genre

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.bizmiz.bookieuz.R
import com.bizmiz.bookieuz.databinding.FragmentGenreBinding
import com.bizmiz.bookieuz.ui.main.genre.view_model.GenreViewModel
import com.bizmiz.bookieuz.utils.ResourceState
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class GenreFragment : Fragment() {
    private val genreViewModel: GenreViewModel by viewModel()
    private var fragments: MutableList<Fragment> = Vector()
    private lateinit var addStationFragmentStateAdapter: AddStationFragmentStateAdapter
    private lateinit var binding: FragmentGenreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        genreViewModel.getCategory()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireActivity(), R.color.secondary_color)
            binding =
                FragmentGenreBinding.bind(inflater.inflate(R.layout.fragment_genre, container, false))
        addStationFragmentStateAdapter = AddStationFragmentStateAdapter(
            activity ?: return binding.root, fragments
        )
        categoryObserve()
        binding.ivSearch.setOnClickListener {
            val navController =
                Navigation.findNavController(requireActivity(), R.id.main_container)
            navController.navigate(R.id.action_drawer_to_searchFragment)
        }
        return binding.root
    }
    private fun categoryObserve() {
        genreViewModel.categoryList.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                ResourceState.SUCCESS -> {
                    binding.loading.visibility = View.GONE
                    val list: ArrayList<String> = arrayListOf()
                       it.data?.data?.forEach {
                           list.add(it.name)
                       }
                        binding.viewPager.offscreenPageLimit = list.size
                        binding.viewPager.adapter = addStationFragmentStateAdapter
                        TabLayoutMediator(
                            binding.tabLayout,
                            binding.viewPager
                        ) { tab: TabLayout.Tab, position: Int ->
                            tab.text = list[position]
                        }.attach()
                    Log.d("list",list.toString())
                }
                ResourceState.ERROR -> {
                    binding.loading.visibility = View.GONE
                    Toast.makeText(requireActivity(), it.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }
}