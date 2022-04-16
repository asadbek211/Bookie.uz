package com.bizmiz.bookieuz.ui.main.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bizmiz.bookieuz.R
import com.bizmiz.bookieuz.databinding.BookItemBinding
import com.bizmiz.bookieuz.databinding.FolkloreItemBinding

class FolkloreAdapter : RecyclerView.Adapter<FolkloreAdapter.ViewHolder>() {
    private var onclick: (position: Int) -> Unit = {}
    fun onClickListener(onclick: (position: Int) -> Unit) {
        this.onclick = onclick
    }

    inner class ViewHolder(private val binding: FolkloreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun getBook(position: Int) {
            binding.bookImages.setImageResource(R.drawable.test_image1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val folkloreItemBinding =
            FolkloreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(folkloreItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.getBook(position)
    }

    override fun getItemCount(): Int = 10
}