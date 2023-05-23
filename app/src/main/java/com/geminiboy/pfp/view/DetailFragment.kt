package com.geminiboy.pfp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.geminiboy.pfp.R
import com.geminiboy.pfp.adapter.AdapterNewsUpdate
import com.geminiboy.pfp.adapter.AdapterProduct
import com.geminiboy.pfp.databinding.FragmentDetailBinding
import com.geminiboy.pfp.databinding.FragmentHomeBinding
import com.geminiboy.pfp.viewmodel.DetailViewModel
import com.geminiboy.pfp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding
    private val detailVM: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayoutDetail()

    }

    private fun setLayoutDetail(){
        detailVM.setDetailProduct()
            detailVM.detailProduct.observe(viewLifecycleOwner){
                if (it != null){
                    Glide.with(requireContext()).load(it.productImage).into(binding.Imgproduct)
                    binding.namaBarang.text = it.name
                    binding.hargaBarang.text = it.price
                    binding.desBarang.text = it.description

                }

            }

    }


}