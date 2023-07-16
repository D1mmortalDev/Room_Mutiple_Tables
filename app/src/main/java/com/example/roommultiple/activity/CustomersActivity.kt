package com.example.roommultiple.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.roommultiple.database.AppDatabase
import com.example.roommultiple.model.Customers
import com.example.roommultiple.databinding.ActivityCustomersBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CustomersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomersBinding
    private lateinit var appDb: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appDb = AppDatabase.invoke(this)

        binding.btnCSave.setOnClickListener(){
            var name = binding.etCName.text.toString()
            var detail = binding.etCDetails.text.toString()
            var customers= Customers(0,name,detail)
            save(customers)
        }
        binding.btnCView.setOnClickListener(){
            view()
        }
        binding.btnCUpdate.setOnClickListener(){
            var id = binding.etCid.text.toString().toInt()
            var name = binding.etCName.text.toString()
            var price = binding.etCDetails.text.toString()
            val customers = Customers(id,name,price)
            update(customers)
        }
        binding.btnCDelete.setOnClickListener() {
            var id = binding.etCid.text.toString().toInt()
            var name = binding.etCName.text.toString()
            var details = binding.etCDetails.toString()
            val customers = Customers(id,name,details)
            delete(customers)
        }
    }
    private fun save(customer: Customers){
        GlobalScope.launch(Dispatchers.IO){
            appDb.getCustomersDao().addCustomer(customer)
        }
        Toast.makeText(applicationContext,"Saved",Toast.LENGTH_SHORT).show()
    }
    private fun view(){
        lateinit var customers:List<Customers>
        GlobalScope.launch(Dispatchers.IO){
            customers= appDb.getCustomersDao().getAllCustomers()
            withContext(Dispatchers.Main){
                Toast.makeText(applicationContext,customers.toString(),Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun update(customers: Customers){
        GlobalScope.launch(Dispatchers.IO){
            appDb.getCustomersDao().updateCustomer(customers)
        }
        Toast.makeText(applicationContext,"Updated!",Toast.LENGTH_LONG).show()
    }
    private fun delete(customers: Customers){
        GlobalScope.launch(Dispatchers.IO){
            appDb.getCustomersDao().deleteCustomer(customers)
        }
        Toast.makeText(applicationContext,"Deleted!",Toast.LENGTH_LONG).show()
    }
}