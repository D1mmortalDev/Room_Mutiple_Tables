package com.example.roommultiple.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.roommultiple.database.AppDatabase
import com.example.roommultiple.model.Products
import com.example.roommultiple.databinding.ActivityProductBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductBinding
    private lateinit var appDb: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appDb =AppDatabase.invoke(this)

        binding.btnSave.setOnClickListener(){
            var name = binding.etName.text.toString()
            var price = binding.etPrice.text.toString().toInt()
            val product = Products(0,name,price)
            save(product)
        }
        binding.btnView.setOnClickListener(){
            view()
        }
        binding.btnUpdate.setOnClickListener(){
            var id = binding.etId.text.toString().toInt()
            var name= binding.etName.text.toString()
            var price= binding.etPrice.text.toString().toInt()
            val product = Products(id,name,price)
            update(product)
        }
        binding.btnDelete.setOnClickListener(){
            var id = binding.etId.text.toString().toInt()
            var name= binding.etName.text.toString()
            var price= binding.etPrice.text.toString().toInt()
            val product = Products(id,name,price)
            delete(product)
        }
    }

    private fun save(products: Products){
        GlobalScope.launch(Dispatchers.IO){
            appDb.getProductsDao().addProduct(products)
        }
        Toast.makeText(applicationContext,"Saved!",Toast.LENGTH_LONG).show()
    }
    private fun view(){
        lateinit var products:List<Products>
        GlobalScope.launch(Dispatchers.IO){
            products = appDb.getProductsDao().getAllProducts()
            withContext(Dispatchers.Main){
                Toast.makeText(applicationContext,products.toString(),Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun update(products: Products){
        GlobalScope.launch(Dispatchers.IO){
            appDb.getProductsDao().updateProduct(products)
        }
        Toast.makeText(applicationContext,"Updated!",Toast.LENGTH_LONG).show()
    }
    private fun delete(products: Products){
        GlobalScope.launch(Dispatchers.IO){
            appDb.getProductsDao().deleteProduct(products)
        }
        Toast.makeText(applicationContext,"Deleted!",Toast.LENGTH_LONG).show()
    }
}