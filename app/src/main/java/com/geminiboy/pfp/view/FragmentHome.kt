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
import com.geminiboy.pfp.adapter.AdapterNewsUpdate
import com.geminiboy.pfp.adapter.AdapterProduct
import com.geminiboy.pfp.databinding.FragmentHomeBinding
import com.geminiboy.pfp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentHome : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private val homeVM : HomeViewModel by viewModels()
    private val productVM : HomeViewModel by viewModels()

    private val imageList = arrayListOf<SlideModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageList.add(SlideModel("https://www.simplilearn.com/ice9/free_resources_article_thumb/what_is_image_Processing.jpg"))
        imageList.add(SlideModel("https://www.seiu1000.org/sites/main/files/main-images/camera_lense_0.jpeg"))

        val sliderLayout = binding.imageSlider
        sliderLayout.setImageList(imageList)

        setLayoutNewsUpdate()
        setLayoutProduct()

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

        productVM.setProduct()
        productVM.product.observe(viewLifecycleOwner){
            if (it != null){
                binding.rvProduct.adapter = AdapterProduct(it)
            }
        }

    }


}