package com.geminiboy.pfp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.geminiboy.pfp.adapter.AdapterProduct
import com.geminiboy.pfp.databinding.FragmentCategoryBinding
import com.geminiboy.pfp.viewmodel.CategoryViewModel
import com.geminiboy.pfp.wrapper.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment() {
    lateinit var binding: FragmentCategoryBinding
    private val  listItemCategoryyyVM : CategoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val getProductId = arguments?.getInt("ID_CATEGORY")
        val getNameCategory = arguments?.getString("NAME_CATEGORY")
        binding.txtCategoryCategory.text = getNameCategory
        print(getProductId)
        print(getNameCategory)
        setLayoutCategory(getProductId!!)
    }

    private fun setLayoutCategory(id : Int){
        listItemCategoryyyVM.setCategoryList(id)

        binding.rvListCategory.layoutManager = GridLayoutManager(requireContext(), 2)
        listItemCategoryyyVM.listProduct.observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading ->{
                    binding.progressBarCateg.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    binding.progressBarCateg.visibility = View.GONE
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }

                is Resource.Success -> {
                    binding.progressBarCateg.visibility = View.GONE
                    binding.contentCategory.visibility = View.VISIBLE
                    val data = it.data!!
                    binding.rvListCategory.adapter = AdapterProduct(data)
                }

            }
        }
    }




}