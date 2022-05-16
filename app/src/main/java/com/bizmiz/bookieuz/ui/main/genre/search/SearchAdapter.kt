package com.bizmiz.bookieuz.ui.main.genre.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bizmiz.bookieuz.R
import com.bizmiz.bookieuz.databinding.WorldBookItemBinding
import com.bizmiz.bookieuz.ui.model.BookDetails
import com.bumptech.glide.Glide

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    var categoryList: List<BookDetails> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    private var onclick: (position: Int) -> Unit = {}
    fun onClickListener(onclick: (position: Int) -> Unit) {
        this.onclick = onclick
    }

    inner class ViewHolder(private val binding: WorldBookItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun getBook(bookDetails: BookDetails) {
            binding.bookImages.setImageResource(R.drawable.test_image2)
            Glide.with(binding.root.context).load(bookDetails.image)
                .into(binding.bookImages)
            binding.container.setOnClickListener {
                onclick.invoke(bookDetails.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val worldBookItemBinding =
            WorldBookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(worldBookItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.getBook(categoryList[position])
    }

    override fun getItemCount(): Int = categoryList.size
}