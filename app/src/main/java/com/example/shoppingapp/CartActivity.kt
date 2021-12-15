package com.example.shoppingapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.data.cartDatabase.UserCart
import com.example.shoppingapp.data.product.ProductEntity
import com.example.shoppingapp.data.userDatabase.UserViewModel
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.content_home_screen.*
import kotlinx.android.synthetic.main.content_home_screen.recyclerView

class CartActivity : AppCompatActivity() {
    private lateinit var mUserViewModel: UserViewModel
    private var adapter1: RecyclerView.Adapter<CartAdapter.CartViewHolder>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        adapter1=CartAdapter()
        layoutManager= LinearLayoutManager(this)
        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val userId:Int = sharedPref.getInt("userId",0)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.getCart(userId).observe(this, Observer {  cart ->changeUI(cart,mUserViewModel)  })
        cartRecyclerView.layoutManager =layoutManager
        cartRecyclerView.adapter = adapter1
    }

    private fun changeUI(cart: UserCart?, mUserViewModel: UserViewModel) {

        if (cart != null) {
            (adapter1 as CartAdapter).setData(cart.products)
            (adapter1 as CartAdapter).setViewModel(mUserViewModel)
            var products : List<ProductEntity> = cart.products
            var price : Int = 0
            for (i in 0..products.size-1){
                price += products[i].productprice!!
            }
            carttotal.setText("$"+price)
        }

    }


}