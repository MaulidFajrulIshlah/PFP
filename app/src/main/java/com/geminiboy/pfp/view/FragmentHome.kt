package com.geminiboy.pfp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.models.SlideModel
import com.geminiboy.pfp.view.adapter.AdapterCategory
import com.geminiboy.pfp.view.adapter.AdapterNewsUpdate
import com.geminiboy.pfp.view.adapter.AdapterProduct
import com.geminiboy.pfp.databinding.FragmentHomeBinding
import com.geminiboy.pfp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentHome : Fragment() {
    lateinit var binding : FragmentHomeBinding
    private val homeVM : HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setLayoutNewsUpdate()
        setLayoutProduct()
        setLayoutToCategory()
        setSliders()
    }
    private fun setLayoutNewsUpdate(){
        binding.rvNewsUpdate.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL, false)

        homeVM.setNewsUpdate()
        homeVM.belanjaan.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.rvNewsUpdate.adapter = AdapterNewsUpdate(it)
            }
        }
    }

    private fun setLayoutProduct(){
        binding.rvProduct.layoutManager = GridLayoutManager(requireContext(), 2)

        homeVM.setProduct()
        homeVM.product.observe(viewLifecycleOwner){
            if (it != null){
                binding.rvProduct.adapter = AdapterProduct(it, true)
            }
        }

    }

    private fun setLayoutToCategory(){
        binding.rvCategory.layoutManager = GridLayoutManager(requireContext(), 4)

        homeVM.setToCategory()
        homeVM.toCateg.observe(viewLifecycleOwner){
            if (it != null){
                binding.rvCategory.adapter = AdapterCategory(it)
            }
        }
    }

    fun setSliders(){
        val imageList = arrayListOf<SlideModel>()
        homeVM.getSliders()
        homeVM.sliders.observe(viewLifecycleOwner){
            imageList.clear()
            for(i in it){
                imageList.add(SlideModel(i.image))
            }
            binding.imageSlider.setImageList(imageList)
        }
    }
}