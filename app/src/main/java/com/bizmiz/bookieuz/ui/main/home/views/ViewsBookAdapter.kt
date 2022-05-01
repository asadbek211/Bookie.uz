package com.bizmiz.bookieuz.ui.main.home.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bizmiz.bookieuz.R
import com.bizmiz.bookieuz.databinding.ViewsBookItemBinding
import com.bizmiz.bookieuz.ui.model.Lastest
import com.bizmiz.bookieuz.ui.model.View
import com.bumptech.glide.Glide

class ViewsBookAdapter : RecyclerView.Adapter<ViewsBookAdapter.ViewHolder>() {
    var viewsList:List<View> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    private var onclick: (position: Int) -> Unit = {}
    fun onClickListener(onclick: (position: Int) -> Unit) {
        this.onclick = onclick
    }

    inner class ViewHolder(private val binding: ViewsBookItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun getBook(view: View) {
            Glide.with(binding.root.context).load(view.images)
                .into(binding.bookImages)
            binding.tvTitle.text = view.name
            binding.bookImages.setOnClickListener {
                onclick.invoke(view.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewsBookItemBinding =
            ViewsBookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewsBookItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.getBook(viewsList[position])
    }

    override fun getItemCount(): Int = viewsList.size
}