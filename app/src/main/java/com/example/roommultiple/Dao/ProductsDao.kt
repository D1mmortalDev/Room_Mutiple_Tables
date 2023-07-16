package com.example.roommultiple.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.roommultiple.model.Products

@Dao
interface ProductsDao {
    @Insert
    fun addProduct(product: Products)

    @Query("SELECT * FROM products")
    fun getAllProducts():List<Products>

    @Update
    fun updateProduct(product: Products)

    @Delete
    fun deleteProduct(product: Products)
}