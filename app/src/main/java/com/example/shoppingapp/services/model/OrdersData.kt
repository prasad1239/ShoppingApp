package com.example.shoppingapp.services.model

import com.google.gson.annotations.SerializedName

data class OrdersData (
    @SerializedName("data") val data : List<Orders>
)