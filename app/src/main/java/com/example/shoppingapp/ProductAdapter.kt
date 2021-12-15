package com.example.shoppingapp

import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoppingapp.data.product.ProductEntity
import kotlinx.android.synthetic.main.banner_pager.view.*
import kotlinx.android.synthetic.main.product.view.*

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder> () {
    /*private var name= arrayOf("Bread","Milk","Rice","Salt")
    private var details= arrayOf("Fresh Bread","Amul Milk","Premium Biryani Rice","Iodated Salt")
    private var price= arrayOf("$5","$15","$25","$5")*/
    private var images =arrayOf(R.drawable.product_01,R.drawable.product_02,R.drawable.product_03,R.drawable.product_04)
    private var productEntity= emptyList<ProductEntity>()
    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var productImage: ImageView
        var productName: TextView
        var productDetails: TextView
        var productCost: TextView
        var card: CardView

        init {
            productImage= itemView.findViewById(R.id.ivProduct)
            productName= itemView.findViewById(R.id.tvProductName)
            productDetails= itemView.findViewById(R.id.tvProductDetail)
            productCost= itemView.findViewById(R.id.tvProductCost)
            card=itemView.findViewById(R.id.cardview)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product, parent,false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        holder.itemView.tvProductName.text = productEntity[position].productname
        holder.itemView.tvProductDetail.text = productEntity[position].productdetail
        holder.itemView.tvProductCost.text = productEntity[position].productprice.toString()
        Glide.with(holder.itemView).load(productEntity.get(position).productImage).into(holder.productImage)
        /*holder.productName.text = name[position]
        holder.productDetails.text = details[position]
        holder.productCost.text = price[position]*/
        //holder.productImage.setImageResource(images[position])
        holder.card.setOnClickListener{
            val intent: Intent = Intent(it.context,ProductDetailsActivity::class.java)
            intent.putExtra("product_name",productEntity[position].productname)
            intent.putExtra("product_image",productEntity[position].productImage)
            intent.putExtra("product_id",productEntity[position].productId)
            intent.putExtra("product_price",productEntity[position].productprice.toString())
            it.context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return productEntity.size
    }
    fun setData(productEntity: List<ProductEntity>) {
        this.productEntity = productEntity
        notifyDataSetChanged()
    }

}





