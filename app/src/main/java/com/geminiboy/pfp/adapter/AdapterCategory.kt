package com.geminiboy.pfp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geminiboy.pfp.databinding.ItemNewsUpdateBinding
import com.geminiboy.pfp.databinding.ItemToCategoryBinding
import com.geminiboy.pfp.model.category.ResponseCategoryItem

class AdapterCategory(private val listCategory : List<ResponseCategoryItem>):
    RecyclerView.Adapter<AdapterCategory.ViewHolder>() {

    class ViewHolder(var binding : ItemToCategoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindCateg(){

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterCategory.ViewHolder {
        val view = ItemToCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterCategory.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}