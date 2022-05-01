package com.bizmiz.bookieuz.ui.main.my_book.favourite_books

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bizmiz.bookieuz.R
import com.bizmiz.bookieuz.databinding.FragmentFavouriteBookBinding

class FavouriteBookFragment : Fragment() {
    private lateinit var binding: FragmentFavouriteBookBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentFavouriteBookBinding.bind(inflater.inflate(R.layout.fragment_favourite_book, container, false))
        return binding.root
    }
}