package com.geminiboy.pfp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.geminiboy.pfp.R
import com.geminiboy.pfp.databinding.FragmentHomeBinding


class FragmentHome : Fragment() {
    lateinit var binding : FragmentHomeBinding
    val imageList = arrayListOf<SlideModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root

        imageList.add(SlideModel("https://www.simplilearn.com/ice9/free_resources_article_thumb/what_is_image_Processing.jpg"))
        imageList.add(SlideModel("https://www.seiu1000.org/sites/main/files/main-images/camera_lense_0.jpeg"))

        val sliderLayout = binding.imageSlider
        sliderLayout.setImageList(imageList)
    }


}