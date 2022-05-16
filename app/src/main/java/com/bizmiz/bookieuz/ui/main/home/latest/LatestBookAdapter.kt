package com.bizmiz.bookieuz.ui.main.home.latest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bizmiz.bookieuz.R
import com.bizmiz.bookieuz.databinding.LatestBookItemBinding
import com.bizmiz.bookieuz.ui.model.Lastest
import com.bizmiz.bookieuz.ui.model.LatestData
import com.bumptech.glide.Glide

class LatestBookAdapter : RecyclerView.Adapter<LatestBookAdapter.ViewHolder>() {

    var latestList:List<Lastest> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    private var onclick: (bookId: Int) -> Unit = {}
    fun onClickListener(onclick: (bookId: Int) -> Unit) {
        this.onclick = onclick
    }

    inner class ViewHolder(private val binding: LatestBookItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun getBook(lastest: Lastest) {
            Glide.with(binding.root.context).load(lastest.image)
                .into(binding.bookImages)
            binding.bookImages.setOnClickListener {
                onclick.invoke(lastest.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val latestBookItemBinding =
            LatestBookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(latestBookItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.getBook(latestList[position])
    }

    override fun getItemCount(): Int = latestList.size
}