package com.example.roommultiple.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Customers (
    @PrimaryKey(autoGenerate = true)
    var cId:Int=0,
    var cname:String,
    var cPrice:String
    )
