package com.example.shoppingapp

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.R
import kotlinx.android.synthetic.main.banner_pager.*
import kotlinx.android.synthetic.main.banner_pager.view.*


class BannerPagerAdapter(


    val images: List<Int>
) : RecyclerView.Adapter<BannerPagerAdapter.BannerPagerViewHolder>() {

    inner class BannerPagerViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerPagerViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.banner_pager,parent, false)
        return BannerPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerPagerViewHolder, position: Int) {
        val curImage = images[position]
        holder.itemView.ivBanner.setImageResource(curImage)

    }

    override fun getItemCount(): Int {
        return images.size

    }
}