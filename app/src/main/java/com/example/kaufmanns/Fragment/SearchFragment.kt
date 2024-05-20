package com.example.kaufmanns.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kaufmanns.R
import com.example.kaufmanns.adapter.MenuAdapter
import com.example.kaufmanns.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter:MenuAdapter
    private val orignalMenuFood= listOf("Burger", "Sandwich","Pizza","Biftek","Makarna", "Salata","Lazanya","Sushi")
   private val orignalMenuItemPrice = listOf("650₺","480₺","750₺","1000₺","600₺","450₺","500₺","1200₺")
    val orignalMenuImage= listOf(
        R.drawable.burger,
        R.drawable.sandwich,
        R.drawable.pizza,
        R.drawable.biftek,
        R.drawable.makarna,
        R.drawable.salata,
        R.drawable.lazanya,
        R.drawable.sushi,

        )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
private val filteredMenuFoodName= mutableListOf<String>()
private val filteredMenuItemPrice= mutableListOf<String>()
private val filteredMenuImage= mutableListOf<Int>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentSearchBinding.inflate(inflater,container,false)
        adapter= MenuAdapter(filteredMenuFoodName,filteredMenuItemPrice,filteredMenuImage, requireContext())
        binding.menuRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        binding.menuRecyclerView.adapter=adapter
        setupSearchView()
        return binding.root

    }
    private fun showAllMenu(){
        filteredMenuFoodName.clear()
        filteredMenuItemPrice.clear()
        filteredMenuImage.clear()

        filteredMenuFoodName.addAll(orignalMenuFood)
        filteredMenuItemPrice.addAll(orignalMenuItemPrice)
        filteredMenuImage.addAll(orignalMenuImage)

        adapter.notifyDataSetChanged()
    }

    private fun setupSearchView(){
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                filterMenuItems(query)
                return true
            }

            override fun onQueryTextChange(newText:String): Boolean {
                filterMenuItems(newText)
                return true
            }
        })

    }
    private fun filterMenuItems(query:String){
        filteredMenuFoodName.clear()
        filteredMenuItemPrice.clear()
        filteredMenuImage.clear()

        orignalMenuFood.forEachIndexed { index, foodName    ->
            if (foodName.contains(query,ignoreCase = true)){
                filteredMenuFoodName.add(foodName)
                filteredMenuItemPrice.add(orignalMenuItemPrice[index])
                filteredMenuImage.add(orignalMenuImage[index])
            }
        }
        adapter.notifyDataSetChanged()
    }



    companion object {

    }
}