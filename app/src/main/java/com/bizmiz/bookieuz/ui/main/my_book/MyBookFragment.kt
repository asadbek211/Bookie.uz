package com.bizmiz.bookieuz.ui.main.my_book

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bizmiz.bookieuz.R
import com.bizmiz.bookieuz.databinding.FragmentMyBookBinding
import com.bizmiz.bookieuz.ui.main.genre.adapters.MyBookPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*

class MyBookFragment : Fragment() {
    private var fragments: MutableList<Fragment> = Vector()
    private lateinit var myBookPagerAdapter: MyBookPagerAdapter
    private lateinit var binding: FragmentMyBookBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        val list: ArrayList<String> = arrayListOf(
            "Esitip atırǵan kitaplarım",
            "Ardaqlı kitaplarım"
        )
        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireActivity(), R.color.secondary_color)
        binding = FragmentMyBookBinding.bind(inflater.inflate(R.layout.fragment_my_book, container, false))
        myBookPagerAdapter = MyBookPagerAdapter(
            activity ?: return binding.root, fragments
        )
        binding.viewPager.offscreenPageLimit = list.size
        binding.viewPager.adapter = myBookPagerAdapter
        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = list[position]
        }.attach()
        return binding.root
    }
}