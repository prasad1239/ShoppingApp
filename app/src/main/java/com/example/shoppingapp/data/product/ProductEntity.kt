package com.example.shoppingapp.data.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Product_Table",indices = arrayOf(Index(value = ["productname"],
    unique = true)))

data class ProductEntity (
    @ColumnInfo(name = "productname")
    val productname: String?,
    val productdetail: String?,
    val productprice: Int?,
    val currency:String,
    val expiryDate:String,
    val productImage:String)
//    val productimageurl: String ="https://www.google.com/imgres?imgurl=https%3A%2F%2Fwww.amul.com%2Ffiles%2Fproducts%2Famul_gold.jpg&imgrefurl=https%3A%2F%2Fwww.amul.com%2Fproducts%2Fmilk.php&tbnid=omJiTWj51y1--M&vet=12ahUKEwjKr5mi08PxAhULBXIKHeLpANMQMygDegUIARDSAQ..i&docid=T29YcOV89tgFuM&w=184&h=214&q=amul%20milk%20images&ved=2ahUKEwjKr5mi08PxAhULBXIKHeLpANMQMygDegUIARDSAQ")
//
{
    @PrimaryKey(autoGenerate = true)
    var productId:Int=0
}