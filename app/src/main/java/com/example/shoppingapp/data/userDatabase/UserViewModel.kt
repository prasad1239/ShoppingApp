package com.example.shoppingapp.data.userDatabase

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.data.cartDatabase.Cart
import com.example.shoppingapp.data.cartDatabase.UserCart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application){

    private val readAllData : LiveData<List<User>>
    private val repository : UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }
    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }

    }
    fun getuser(email: String, password : String): LiveData<List<User>> {
        val variable :LiveData<List<User>> = repository.getUser(email,password)
        return variable

    }

    fun addToCart(userId:Int,productId:Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addToCart(Cart(userId,productId))
        }
    }
    fun getCart(userId: Int): LiveData<UserCart> {
        return repository.viewCart(userId)
    }
     fun  deleteItemFromCart(id: Int,productId: Int) {
         viewModelScope.launch(Dispatchers.IO) {
             repository.deleteItemFromCart(id, productId)
         }
     }
}