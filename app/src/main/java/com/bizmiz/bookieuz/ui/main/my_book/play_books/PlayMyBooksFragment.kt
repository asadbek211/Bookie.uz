package com.bizmiz.bookieuz.ui.main.my_book.play_books

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bizmiz.bookieuz.R
import com.bizmiz.bookieuz.databinding.FragmentPlayMyBooksBinding

class PlayMyBooksFragment : Fragment() {
    private lateinit var binding: FragmentPlayMyBooksBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayMyBooksBinding.bind(inflater.inflate(R.layout.fragment_play_my_books, container, false))
        return binding.root
    }
}