package com.example.shoppingapp.data.product


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(private val productRepository: ProductRepository): ViewModel(){
    fun readProduct(): LiveData<List<ProductEntity>> {
        return productRepository.readProduct()
    }
    fun insertProduct(products: List<ProductEntity>){
        viewModelScope.launch(Dispatchers.IO){
            productRepository.insertAllProducts(products)
        }
    }
}
