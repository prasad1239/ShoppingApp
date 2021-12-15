package com.example.shoppingapp.data.cartDatabase


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["id","productId"])
data class Cart(
    val id : Int,
    val productId : Int
)
