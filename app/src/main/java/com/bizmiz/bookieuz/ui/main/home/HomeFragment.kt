package com.bizmiz.bookieuz.ui.main.home

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsController
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.bizmiz.bookieuz.R
import com.bizmiz.bookieuz.databinding.FragmentHomeBinding
import com.bizmiz.bookieuz.ui.auth.api.ApiClient
import com.bizmiz.bookieuz.ui.auth.api.NetworkHelper
import com.bizmiz.bookieuz.ui.main.genre.AddStationFragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private var fragments: MutableList<Fragment> = Vector()
    private lateinit var homePagerAdapter: HomePagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        homePagerAdapter = HomePagerAdapter(
            activity ?: return binding.root, fragments
        )
        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireActivity(), R.color.white)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            requireActivity().window.decorView.windowInsetsController?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        }
        binding = FragmentHomeBinding.bind(inflater.inflate(R.layout.fragment_home, container, false))
        binding.trendContainer.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(), R.id.basicNavigation)
            navController.navigate(R.id.home_to_viewBook)
        }
        val list: ArrayList<String> = arrayListOf(
            "Jańa qosılǵan",
            "Eń kóp kórilgen"
        )
        binding.viewPager.offscreenPageLimit = list.size
        binding.viewPager.adapter = homePagerAdapter
        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = list[position]
        }.attach()
        return binding.root
    }
}