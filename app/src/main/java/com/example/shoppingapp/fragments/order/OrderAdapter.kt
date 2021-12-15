package com.example.shoppingapp.fragments.order
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.R
import com.example.shoppingapp.services.model.Orders
class OrderAdapter : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
    private var orders=emptyList<Orders>()
    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var productImage: ImageView
        var orderDate: TextView
        var orderPrice: TextView
        var card: CardView
        init {
            productImage= itemView.findViewById(R.id.ivOrder)
            orderDate= itemView.findViewById(R.id.tvOrderDate)
            orderPrice= itemView.findViewById(R.id.tvOrderPrice)
            card=itemView.findViewById(R.id.orderCardView)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_card_view, parent,false)
        return OrderViewHolder(view)
    }
    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.orderDate.setText(orders[position].orderDate)
        holder.orderPrice.setText(orders[position].orderTotal.toString())
    }
    override fun getItemCount(): Int {
        return orders.size
    }
    fun setData(orders: List<Orders>) {
        this.orders = orders
        notifyDataSetChanged()
    }
}