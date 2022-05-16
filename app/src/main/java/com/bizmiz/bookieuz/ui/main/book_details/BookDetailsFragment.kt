package com.bizmiz.bookieuz.ui.main.book_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.bizmiz.bookieuz.R
import com.bizmiz.bookieuz.databinding.FragmentBookDetailsBinding
import com.bizmiz.bookieuz.ui.model.AudioDetails
import com.bizmiz.bookieuz.utils.ResourceState
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookDetailsFragment : Fragment() {
    private lateinit var binding: FragmentBookDetailsBinding
    private val bookDetailsViewModel: BookDetailsViewModel by viewModel()
    private var bookId = 0
    private var audios: List<AudioDetails>? = null
    private  var bookName = ""
    private  var bookAuthor = ""
    private  var dublyajAuthor = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bookId = requireArguments().getInt("bookId", 0)
        if (bookId != 0) {
            bookDetailsViewModel.getDetails(bookId)
        }
        requireActivity().window.statusBarColor =
            ContextCompat.getColor(requireActivity(), R.color.white)
        binding = FragmentBookDetailsBinding.bind(
            inflater.inflate(
                R.layout.fragment_book_details,
                container,
                false
            )
        )
        binding.ivBack.setOnClickListener {
            val navController =
                Navigation.findNavController(requireActivity(), R.id.basicNavigation)
            navController.popBackStack()
        }
        binding.imgPlay.setOnClickListener {
            val bundle = bundleOf(
                "audios" to audios,
                "bookName" to bookName,
                "bookAuthor" to bookAuthor,
                "dublyajAuthor" to dublyajAuthor
            )
            val navController =
                Navigation.findNavController(requireActivity(), R.id.basicNavigation)
            navController.navigate(R.id.viewBook_to_audioPlayer,bundle)
        }
        detailsDataObserve()
        return binding.root
    }

    private fun detailsDataObserve() {
        bookDetailsViewModel.detailsData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                ResourceState.SUCCESS -> {
                    if (it.data != null) {
                        audios = it.data.audios
                        bookName = it.data.book.name
                        bookAuthor = it.data.book.author
                        dublyajAuthor = it.data.book.dublyaj_actor_name
                        Glide.with(this).load(it.data.book.image)
                            .into(binding.bookImages)
                        binding.tvBookTitle.text = it.data.book.name
                        binding.tvBookAuthor.text = it.data.book.author
                        binding.tvBookDescription.text = it.data.book.description
                        binding.views.text = it.data.book.view.toString()
                        binding.sectionCount.text = "Bo'limlar soni: ${it.data.audios.size}"
                    }
                }
                ResourceState.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}