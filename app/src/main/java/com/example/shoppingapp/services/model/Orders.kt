package com.example.shoppingapp.services.model

import com.google.gson.annotations.SerializedName

data class Orders(
    @SerializedName("order id")
    var orderId: Int,
    @SerializedName("user id")
    var userId: Int,
    @SerializedName("order date")
    var orderDate: String,
    @SerializedName("order total")
    var orderTotal: Int,
    val data: List<Products>
)