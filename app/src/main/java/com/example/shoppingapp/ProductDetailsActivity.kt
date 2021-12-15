package com.example.shoppingapp

import android.content.Context
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.bumptech.glide.Glide
import com.example.shoppingapp.data.cartDatabase.UserCart
import com.example.shoppingapp.data.userDatabase.UserViewModel
import kotlinx.android.synthetic.main.activity_product_details.*

class ProductDetailsActivity() : AppCompatActivity(), Parcelable {
    constructor(parcel: Parcel) : this() {
    }
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        val productImage:ImageView=findViewById(R.id.productImage)
        val intent : Intent = getIntent()
        val product_title : String? = intent.getStringExtra("product_name")
        val productId:Int=intent.getIntExtra("product_id",0)
        val productImageValue=intent.getStringExtra("product_image")
        val productPrice=intent.getStringExtra("product_price")
        tvprice.setText("\$"+productPrice)
        toolbar_product.setTitle(product_title)
        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val userId:Int = sharedPref.getInt("userId",0)
        Glide.with(applicationContext).load(productImageValue).into(productImage)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        btnAddToCart.setOnClickListener {
            if(productId!=0){
                mUserViewModel.addToCart(userId,productId)
                mUserViewModel.getCart(userId).observe(this, Observer { cart->viewCart(cart) })
            }

        }
    }

    private fun viewCart(cart: UserCart?) {
        Log.d("cart",cart.toString())
        if (cart != null) {
            Log.d("cart",cart.products.size.toString())
            cart.products.get(0).productname?.let { Log.d("cart", it) }
        }

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductDetailsActivity> {
        override fun createFromParcel(parcel: Parcel): ProductDetailsActivity {
            return ProductDetailsActivity(parcel)
        }

        override fun newArray(size: Int): Array<ProductDetailsActivity?> {
            return arrayOfNulls(size)
        }
    }
}