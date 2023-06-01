package com.geminiboy.pfp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.geminiboy.pfp.databinding.ListRiwayatBinding
import com.geminiboy.pfp.model.transaksi_history.ResponseTransactionHistoryItem

class AdapterTransactionHistory(private val listRiwayat : List<ResponseTransactionHistoryItem>):
    RecyclerView.Adapter<AdapterTransactionHistory.ViewHolder>() {

    class ViewHolder(var binding : ListRiwayatBinding): RecyclerView.ViewHolder(binding.root){
        fun bindFavoriteList(dataRiwayat : ResponseTransactionHistoryItem){
            binding.lRNama.text = dataRiwayat.name
            binding.lRNama.text = dataRiwayat.price.toString()

            Glide.with(itemView).load(dataRiwayat.picture).into(binding.lRGambarProduct)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ListRiwayatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindFavoriteList(listRiwayat[position])

    }

    override fun getItemCount(): Int = listRiwayat.size
}