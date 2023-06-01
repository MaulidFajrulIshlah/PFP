package com.geminiboy.pfp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.geminiboy.pfp.R
import com.geminiboy.pfp.databinding.FragmentTransactionHistoryBinding
import com.geminiboy.pfp.view.adapter.AdapterTransactionHistory
import com.geminiboy.pfp.viewmodel.TransactionHistoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionHistoryFragment : Fragment() {
    private var binding: FragmentTransactionHistoryBinding? = null
    private val transVM: TransactionHistoryViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionHistoryBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTransHistory()
    }

    fun setTransHistory(){
        transVM.getTransHistory()
        transVM.transHistory.observe(viewLifecycleOwner){
            binding?.apply {
                rvTrans.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                rvTrans.adapter = AdapterTransactionHistory(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}