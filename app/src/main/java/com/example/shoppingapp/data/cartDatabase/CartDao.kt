package com.example.shoppingapp.data.cartDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToCart(cart: Cart)

    @Query("SELECT * FROM Cart ORDER BY id ASC")
    fun getcartdetail() : LiveData<List<Cart>>
}

