package com.bizmiz.bookieuz.ui.main.genre

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bizmiz.bookieuz.R
import com.bizmiz.bookieuz.databinding.FragmentGenreBinding

class GenreFragment : Fragment() {
    private lateinit var binding: FragmentGenreBinding
    private lateinit var popularAdapter: PopularAdapter
    private lateinit var folkloreAdapter: FolkloreAdapter
    private lateinit var poesyAdapter: PoesyAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentGenreBinding.bind(inflater.inflate(R.layout.fragment_genre, container, false))
        popularAdapter = PopularAdapter()
        folkloreAdapter = FolkloreAdapter()
        poesyAdapter = PoesyAdapter()
        binding.genrePopularRecView.adapter = popularAdapter
        binding.genreFolkloreRecView.adapter = folkloreAdapter
        binding.genrePoesyRecView.adapter = poesyAdapter
        return binding.root
    }
}