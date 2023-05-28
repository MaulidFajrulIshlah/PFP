package com.geminiboy.pfp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.geminiboy.pfp.databinding.ItemListCategoryBinding
import com.geminiboy.pfp.databinding.ItemNewsUpdateBinding
import com.geminiboy.pfp.model.category.ResponseCategoryItem

class AdapterListCategory(private val itemCategoryGrid : List<ResponseCategoryItem>):
    RecyclerView.Adapter<AdapterListCategory.ViewHolder>() {

    class ViewHolder(var binding : ItemListCategoryBinding) : RecyclerView.ViewHolder(binding.root)  {
        fun bindCategoryItem(dataCategory : ResponseCategoryItem){
            with(itemView){
                binding.apply {
                    binding.txtNamaBarangCategory.text = dataCategory.name

                    Glide.with(itemView).load(dataCategory.image).into(binding.imgCategoryList)

                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterListCategory.ViewHolder {
        val view = ItemListCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterListCategory.ViewHolder, position: Int) {
        holder.bindCategoryItem(itemCategoryGrid[position])
    }

    override fun getItemCount(): Int = itemCategoryGrid.size
}