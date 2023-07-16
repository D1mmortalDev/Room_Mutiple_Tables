package com.example.roommultiple

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roommultiple.activity.CustomersActivity
import com.example.roommultiple.activity.ProductActivity
import com.example.roommultiple.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnProducts.setOnClickListener{
            val intent = Intent(this,ProductActivity::class.java)
            startActivity(intent)
        }
        binding.btnCustomers.setOnClickListener{
            val intent = Intent(this,CustomersActivity::class.java)
            startActivity(intent)
        }
    }
}