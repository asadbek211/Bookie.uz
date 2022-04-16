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

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var bookAdapter: BookAdapter
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireActivity(), R.color.white)
            requireActivity().window.decorView.windowInsetsController?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        binding = FragmentHomeBinding.bind(inflater.inflate(R.layout.fragment_home, container, false))
        bookAdapter = BookAdapter()
        binding.homeBooksRecView.adapter = bookAdapter
        bookAdapter.onClickListener {
            val navController = Navigation.findNavController(requireActivity(), R.id.basicNavigation)
            navController.navigate(R.id.home_to_viewBook)
        }
        return binding.root
    }
}