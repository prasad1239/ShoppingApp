package com.example.shoppingapp

import android.content.Context
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoppingapp.data.product.ProductEntity
import com.example.shoppingapp.data.userDatabase.UserViewModel

class CartAdapter : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private var productEntity= emptyList<ProductEntity>()
    private var mUserViewModel: UserViewModel? = null

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var productImage: ImageView
        var productName: TextView
        var productPrice: TextView
        var deleteBtn:Button
        var card: CardView

        init {
            productImage= itemView.findViewById(R.id.ivCart)
            productName= itemView.findViewById(R.id.tvCartItemName)
            productPrice= itemView.findViewById(R.id.tvCartItemPrice)
            card=itemView.findViewById(R.id.cartCardView)
            deleteBtn=itemView.findViewById(R.id.cartDeleteBtn)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_cardview, parent,false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.productName.setText(productEntity[position].productname+"-"+productEntity[position].productdetail)
        holder.productPrice.setText(productEntity[position].productprice.toString())
        Glide.with(holder.itemView).load(productEntity.get(position).productImage).into(holder.productImage)
        holder.deleteBtn.setOnClickListener {
            val userId:Int =it.context.getSharedPreferences("myPref", Context.MODE_PRIVATE).getInt("userId",0)

            mUserViewModel?.deleteItemFromCart(userId,productEntity[position].productId)
            notifyDataSetChanged()
        }

    }

    override fun getItemCount(): Int {
        return productEntity.size
    }

    fun setData(productEntity: List<ProductEntity>) {
        this.productEntity = productEntity
        notifyDataSetChanged()
    }
    fun setViewModel(userViewModel: UserViewModel){
        this.mUserViewModel=userViewModel
    }
}