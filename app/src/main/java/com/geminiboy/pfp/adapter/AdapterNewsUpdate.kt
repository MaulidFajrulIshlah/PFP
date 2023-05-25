package com.geminiboy.pfp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.geminiboy.pfp.databinding.ItemNewsUpdateBinding
import com.geminiboy.pfp.model.news.ResponseNewsItem

class AdapterNewsUpdate(private val listNewsUpdate: List<ResponseNewsItem>):
    RecyclerView.Adapter<AdapterNewsUpdate.ViewHolder>() {


    class ViewHolder(var binding : ItemNewsUpdateBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindNewsUpdate(dataNews: ResponseNewsItem){
            binding.tittleNewsUpdate.text = dataNews.title
            binding.contentNewsUpdate.text = dataNews.content

            Glide.with(itemView).load(dataNews.newsImage).into(binding.imgNewsUpdate)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterNewsUpdate.ViewHolder {
        val view = ItemNewsUpdateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterNewsUpdate.ViewHolder, position: Int) {
        holder.bindNewsUpdate(listNewsUpdate[position])
    }

    override fun getItemCount(): Int = listNewsUpdate.size
}