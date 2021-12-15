package com.example.shoppingapp.services.model
import com.google.gson.annotations.SerializedName

data class Products(
    @SerializedName("item name")
    var itemName: String,
    @SerializedName("desc")
    var desc: String,
    @SerializedName("price")
    var price: Int,
    @SerializedName("currency")
    var currency: String,
    @SerializedName("expiry date")
    var expiryDate: String,
    @SerializedName("quantity")
    var quantity: String

)