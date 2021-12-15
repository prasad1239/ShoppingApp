package com.example.shoppingapp.data.product

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface ProductDao {

    @Query("SELECT * FROM product_table ORDER BY productId ASC")
    fun readProduct(): LiveData<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(productEntity: ProductEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(products: List<ProductEntity>)
}

