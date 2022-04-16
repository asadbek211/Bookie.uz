package com.bizmiz.bookieuz.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bizmiz.bookieuz.R
import com.bizmiz.bookieuz.databinding.BookItemBinding

class BookAdapter : RecyclerView.Adapter<BookAdapter.ViewHolder>() {
    var imageList: ArrayList<Int> = arrayListOf(
        R.drawable.test_image1,
        R.drawable.test_image2,
        R.drawable.test_image3,
        R.drawable.test_image4,
        R.drawable.test_image1,
        R.drawable.test_image2,
        R.drawable.test_image3,
        R.drawable.test_image4
    )
    private var onclick: (position: Int) -> Unit = {}
    fun onClickListener(onclick: (position: Int) -> Unit) {
        this.onclick = onclick
    }

    inner class ViewHolder(private val binding: BookItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun getBook(position: Int) {
            binding.bookImages.setImageResource(imageList[position])
            binding.bookImages.setOnClickListener {
                onclick.invoke(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bookItemBinding =
            BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(bookItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.getBook(position)
    }

    override fun getItemCount(): Int = imageList.size
}