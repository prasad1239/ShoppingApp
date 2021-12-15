package com.example.shoppingapp.data.product


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProductViewModelFactory (private val productRepository: ProductRepository):ViewModelProvider.Factory {
    override fun <T :ViewModel?> create(modelClass: Class<T>): T {
        return ProductViewModel(productRepository) as T

    }
}
