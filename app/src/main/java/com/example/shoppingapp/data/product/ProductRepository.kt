package com.example.shoppingapp.data.product

import androidx.lifecycle.LiveData
import com.example.shoppingapp.data.product.ProductDao
import com.example.shoppingapp.data.product.ProductEntity

class ProductRepository(private val productDao: ProductDao) {
    fun readProduct(): LiveData<List<ProductEntity>>{
        return productDao.readProduct()
    }

    suspend fun insertProduct(productEntity: ProductEntity){
        productDao.insertProduct(productEntity)
    }
    suspend fun insertAllProducts(products:List<ProductEntity>){
        productDao.insert(products)
    }
}
