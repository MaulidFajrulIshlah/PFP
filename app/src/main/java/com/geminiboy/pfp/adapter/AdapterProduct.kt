package com.geminiboy.pfp.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.geminiboy.pfp.R
import com.geminiboy.pfp.databinding.ItemNewsUpdateBinding
import com.geminiboy.pfp.databinding.ItemProductBinding
import com.geminiboy.pfp.model.product.ResponseProductItem

class AdapterProduct(private val listProduct: List<ResponseProductItem>,
                     private val isHome: Boolean) : RecyclerView.Adapter<AdapterProduct.ViewHolder>() {
    class ViewHolder(var binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindProduct(dataProduct : ResponseProductItem, isHome: Boolean){
            with(itemView){
                binding.apply {
                    binding.namaBarang.text = dataProduct.name
                    binding.hargaBarang.text = dataProduct.price
                    Glide.with(itemView).load(dataProduct.productImage).into(binding.imgProduct)

                    cardViewProduct.setOnClickListener{
                        val bundle = Bundle().apply {
                            putInt("ID", dataProduct.idProduct.toInt())
                        }
                        if (isHome) {
                            it.findNavController().navigate(R.id.action_fragmentHome_to_detailFragment, bundle)
                        } else {
                            it.findNavController().navigate(R.id.action_categoryFragment_to_detailFragment, bundle)
                        }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterProduct.ViewHolder {
        val view = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterProduct.ViewHolder, position: Int) {
        holder.bindProduct(listProduct[position], isHome)
    }

    override fun getItemCount(): Int = listProduct.size
}