package com.example.kaufmanns.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kaufmanns.R
import com.example.kaufmanns.adapter.BuyAgainAdapter
import com.example.kaufmanns.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

private lateinit var binding:FragmentHistoryBinding
private lateinit var buyAgainAdapter: BuyAgainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHistoryBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        setupRecyclerView()
        return binding.root
    }
    private fun setupRecyclerView(){
        val buyAgainFoodName= arrayListOf("Yemek 1", "Yemek 2", "Yemek 3")
        val buyAgainFoodPrice= arrayListOf("650₺","480₺","750₺")
        val buyAgainFoodImage = arrayListOf(R.drawable.burger,R.drawable.sushi,R.drawable.salata)
        buyAgainAdapter= BuyAgainAdapter(buyAgainFoodName,buyAgainFoodPrice,buyAgainFoodImage)
        binding.BuyAgainRecyclerView.adapter=buyAgainAdapter
        binding.BuyAgainRecyclerView.layoutManager=LinearLayoutManager(requireContext())
    }

    companion object {

}}