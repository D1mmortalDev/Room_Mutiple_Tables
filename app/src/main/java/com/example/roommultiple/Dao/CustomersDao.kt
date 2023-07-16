package com.example.roommultiple.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.roommultiple.model.Customers

@Dao
interface CustomersDao {
    @Insert
    fun addCustomer(customer: Customers)

    @Query("SELECT * FROM customers")
    fun getAllCustomers():List<Customers>

    @Update
    fun updateCustomer(customer: Customers)

    @Delete
    fun deleteCustomer(customer: Customers)
}