package com.example.shoppingapp.services.model

import com.google.gson.annotations.SerializedName

data class ProductsData (
    @SerializedName("data") val data : List<Products>
)