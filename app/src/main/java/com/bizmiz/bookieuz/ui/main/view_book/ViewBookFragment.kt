package com.bizmiz.bookieuz.ui.main.view_book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bizmiz.bookieuz.R
import com.bizmiz.bookieuz.databinding.FragmentViewBookBinding

class ViewBookFragment : Fragment() {
    private lateinit var binding: FragmentViewBookBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewBookBinding.bind(
            inflater.inflate(
                R.layout.fragment_view_book,
                container,
                false
            )
        )
        binding.ivBack.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(), R.id.basicNavigation)
            navController.popBackStack()
        }
        return binding.root
    }
}