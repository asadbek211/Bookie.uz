package com.bizmiz.bookieuz.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.AnimBuilder
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.bizmiz.bookieuz.R
import com.bizmiz.bookieuz.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var leftAnim:Animation
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        leftAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_left)
        requireActivity().window.setFlags(
            0,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        binding =
            FragmentMainBinding.bind(inflater.inflate(R.layout.fragment_main, container, false))
            return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(requireActivity(), R.id.basicNavigation)
        binding.bottomNavView.setupWithNavController(navController)

    }
}