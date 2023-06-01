package com.geminiboy.pfp.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.geminiboy.pfp.R
import com.geminiboy.pfp.databinding.ItemNewsUpdateBinding
import com.geminiboy.pfp.databinding.ItemToCategoryBinding
import com.geminiboy.pfp.model.category.ResponseCategoryItem

class AdapterCategory(private val listCategory : List<ResponseCategoryItem>):
    RecyclerView.Adapter<AdapterCategory.ViewHolder>() {

    class ViewHolder(var binding : ItemToCategoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindCateg(dataToCateg : ResponseCategoryItem){
            with(itemView){
                binding.apply {
                    binding.namaCateg.text = dataToCateg.name
                    Glide.with(itemView).load(dataToCateg.image).into(binding.imgCateg)

                    cardViewToCategory.setOnClickListener {
                        val bundleToC = Bundle().apply {
                            putInt("ID_CATEGORY", dataToCateg.id.toInt())
                            putString("NAME_CATEGORY", dataToCateg.name)
                        }
                        it.findNavController().navigate(R.id.action_fragmentHome_to_categoryFragment, bundleToC)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemToCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindCateg(listCategory[position])
    }

    override fun getItemCount(): Int = listCategory.size
}