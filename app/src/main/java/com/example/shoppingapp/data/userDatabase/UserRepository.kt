package com.example.shoppingapp.data.userDatabase

import androidx.lifecycle.LiveData
import com.example.shoppingapp.data.cartDatabase.Cart
import com.example.shoppingapp.data.cartDatabase.UserCart
import com.example.shoppingapp.data.userDatabase.UserDao
import com.example.shoppingapp.data.userDatabase.User

class UserRepository(private  val userDao: UserDao){

    val readAllData: LiveData<List<User>> = userDao.readAllData()
    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
    fun getUser(email : String, password : String): LiveData<List<User>> {
        val variable :LiveData<List<User>> = userDao.getlogindetail(email,password)
        return variable

    }
    suspend fun addToCart(cart: Cart){
        userDao.addToCart(cart)
    }
    fun viewCart(userId:Int): LiveData<UserCart> {
        return userDao.getUserCartProducts(userId)
    }
    suspend fun deleteItemFromCart(id: Int,productId: Int){
        userDao.deleteItemFromCart(id, productId)
    }
}