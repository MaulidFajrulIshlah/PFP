package com.geminiboy.pfp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.geminiboy.pfp.databinding.ItemNewsUpdateBinding
import com.geminiboy.pfp.databinding.ListFavoriteBinding
import com.geminiboy.pfp.model.favourite.ResponseFavouriteItem

class AdapterFavourite(private val listFavorite : List<ResponseFavouriteItem>):
    RecyclerView.Adapter<AdapterFavourite.ViewHolder>() {

    class ViewHolder(var binding : ListFavoriteBinding): RecyclerView.ViewHolder(binding.root){
            fun bindFavoriteList(dataFav : ResponseFavouriteItem){
                binding.namaFav.text = dataFav.name
                binding.hargaFav.text = dataFav.price.toString()

                Glide.with(itemView).load(dataFav.productImage).into(binding.imgFav)
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ListFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindFavoriteList(listFavorite[position])

    }

    override fun getItemCount(): Int = listFavorite.size
}