package com.example.kaufmanns.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kaufmanns.CongratsBottomSheet
import com.example.kaufmanns.PayOutActivity
import com.example.kaufmanns.R
import com.example.kaufmanns.adapter.CartAdapter
import com.example.kaufmanns.databinding.FragmentCartBinding

class CartFragment : Fragment(){
    private lateinit var binding: FragmentCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)

        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater,container,false)
        val cartFoodName= listOf("Burger", "Sandwich","Pizza","Biftek","Makarna", "Salata","Lazanya","Sushi")
        val cartItemPrice = listOf("650₺","480₺","750₺","1000₺","600₺","450₺","500₺","1200₺")
        val cartImage= listOf(
            R.drawable.burger,
            R.drawable.sandwich,
            R.drawable.pizza,
            R.drawable.biftek,
            R.drawable.makarna,
            R.drawable.salata,
            R.drawable.lazanya,
            R.drawable.sushi,

        )
        val adapter=CartAdapter(ArrayList(cartFoodName),ArrayList(cartItemPrice),ArrayList(cartImage))
        binding.cartRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        binding.cartRecyclerView.adapter=adapter
        binding.proceedButton.setOnClickListener{
            val intent=Intent(requireContext(),PayOutActivity::class.java)
            startActivity(intent)
        }


        return binding.root
    }

    companion object {
    }
}