package com.bizmiz.bookieuz.ui.main.genre.karakalpak_folklor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bizmiz.bookieuz.R
import com.bizmiz.bookieuz.databinding.FolkloreBookItemBinding
import com.bizmiz.bookieuz.ui.model.DataXX
import com.bumptech.glide.Glide

class FolkSongsAdapter : RecyclerView.Adapter<FolkSongsAdapter.ViewHolder>() {
    private var onclick: (position: Int) -> Unit = {}
    fun onClickListener(onclick: (position: Int) -> Unit) {
        this.onclick = onclick
    }
    var categoryList:ArrayList<DataXX> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    inner class ViewHolder(private val binding: FolkloreBookItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun getBook(position: Int,dataXX: DataXX) {
            Glide.with(binding.root.context).load(dataXX.images)
                .into(binding.bookImages)
            binding.tvTitle.text = dataXX.name
            binding.container.setOnClickListener {
                onclick.invoke(dataXX.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val folkloreBookItemBinding =
            FolkloreBookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(folkloreBookItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.getBook(position,categoryList[position])
    }

    override fun getItemCount(): Int = categoryList.size
}