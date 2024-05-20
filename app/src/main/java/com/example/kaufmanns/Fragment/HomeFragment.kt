package com.example.kaufmanns.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.kaufmanns.MenuBottomSheetFragment
import com.example.kaufmanns.R
import com.example.kaufmanns.adapter.PopularAdapter
import com.example.kaufmanns.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewAllMenu.setOnClickListener {
            val bottomSheetDialog = MenuBottomSheetFragment()
            bottomSheetDialog.show(childFragmentManager, "Test")
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.banner1, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner2, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner3, ScaleTypes.FIT))

        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imageList)
        imageSlider.setImageList(imageList, ScaleTypes.FIT)
        imageSlider.setItemClickListener(object:ItemClickListener{
            override fun doubleClick(position: Int) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(position: Int) {
                val itemPosition=imageList[position]
                val itemMessage="Selected Image $position"
                Toast.makeText(requireContext(),itemMessage,Toast.LENGTH_SHORT).show()
            }
        })
        val foodName= listOf("Burger", "Sandwich","Pizza","Biftek","Makarna", "Salata","Lazanya","Sushi")
        val Price= listOf("650₺","480₺","750₺","1000₺","600₺","450₺","500₺","1200₺")
        val populerFoodImages= listOf(
            R.drawable.burger,
            R.drawable.sandwich,
            R.drawable.pizza,
            R.drawable.biftek,
            R.drawable.makarna,
            R.drawable.salata,
            R.drawable.lazanya,
            R.drawable.sushi,)
        val adapter=PopularAdapter(foodName,Price,populerFoodImages,requireContext())
        binding.PopulerRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        binding.PopulerRecyclerView.adapter=adapter

    }

    companion object{

    }
}
