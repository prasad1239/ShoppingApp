package com.example.shoppingapp.services
import com.example.shoppingapp.services.model.OrdersData
import com.example.shoppingapp.services.model.ProductsData
import retrofit2.Call
import retrofit2.http.POST

interface ApiInterface {
    @POST("getorders")
    fun getOrders(): Call<OrdersData>

    @POST("login")
    fun getProducts():Call<ProductsData>

//    @POST("getorders")
//    fun getOrders(): Call<OrdersData>
}
