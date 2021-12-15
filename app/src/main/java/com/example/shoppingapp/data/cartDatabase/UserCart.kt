package com.example.shoppingapp.data.cartDatabase


import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.shoppingapp.data.product.ProductEntity
import com.example.shoppingapp.data.userDatabase.User

data class UserCart(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "productId",
        associateBy = Junction(Cart::class)
    )
    val products: List<ProductEntity>
)
