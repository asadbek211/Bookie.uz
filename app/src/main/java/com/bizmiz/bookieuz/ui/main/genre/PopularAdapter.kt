package com.bizmiz.bookieuz.ui.main.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bizmiz.bookieuz.R
import com.bizmiz.bookieuz.databinding.BookItemBinding
import com.bizmiz.bookieuz.databinding.PopularItemBinding

class PopularAdapter : RecyclerView.Adapter<PopularAdapter.ViewHolder>() {
    private var onclick: (position: Int) -> Unit = {}
    fun onClickListener(onclick: (position: Int) -> Unit) {
        this.onclick = onclick
    }

    inner class ViewHolder(private val binding: PopularItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun getBook(position: Int) {
            binding.bookImages.setImageResource(R.drawable.test_image2)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val popularItemBinding =
            PopularItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(popularItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.getBook(position)
    }

    override fun getItemCount(): Int = 10
}