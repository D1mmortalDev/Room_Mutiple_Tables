package com.example.roommultiple.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roommultiple.Dao.CustomersDao
import com.example.roommultiple.Dao.ProductsDao
import com.example.roommultiple.model.Customers
import com.example.roommultiple.model.Products

@Database(
    entities = [Products::class,Customers::class],
    version = 1
)
abstract class AppDatabase:RoomDatabase() {
    abstract fun getProductsDao():ProductsDao
    abstract fun getCustomersDao():CustomersDao
    companion object{
        @Volatile
        private  var instance:AppDatabase?=null
        private val LOCK = Any()

        operator fun invoke(context:Context) = instance?: synchronized(LOCK){
            instance ?:buildDatabase(context).also { AppDatabase
                instance = it
            }
        }
        private fun buildDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_db"
            ).build()
        }
    }
