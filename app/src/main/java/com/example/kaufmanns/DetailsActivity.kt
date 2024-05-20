package com.example.kaufmanns

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kaufmanns.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
binding =ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
val foodName=intent.getStringExtra("MenuItemName")
val foodImage=intent.getIntExtra("MenuItemImage", 0)
        binding.detailFoodName.text=foodName
        binding.DetailFoodImage.setImageResource(foodImage)
        binding.imageButtonB.setOnClickListener{
            finish()
        }
    }
}