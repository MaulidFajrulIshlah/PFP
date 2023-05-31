package com.geminiboy.pfp.view

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.geminiboy.pfp.R
import com.geminiboy.pfp.adapter.AdapterFavourite
import com.geminiboy.pfp.adapter.AdapterProduct
import com.geminiboy.pfp.databinding.FragmentFavoritBinding
import com.geminiboy.pfp.databinding.FragmentHomeBinding
import com.geminiboy.pfp.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class FragmentFavorit : Fragment() {

    lateinit var binding : FragmentFavoritBinding
    private val favVM : FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoritBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayoutFavoriteList()
    }

    private fun setLayoutFavoriteList(){

        binding.favoriteReyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        favVM.setFavoriteList()

        favVM.favorite.observe(viewLifecycleOwner){
            if (it != null){
                binding.favoriteReyclerView.adapter = AdapterFavourite(it)
            }
        }
    }


}