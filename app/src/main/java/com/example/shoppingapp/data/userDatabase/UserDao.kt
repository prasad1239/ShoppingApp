package com.example.shoppingapp.data.userDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shoppingapp.data.cartDatabase.Cart
import com.example.shoppingapp.data.cartDatabase.UserCart
import com.example.shoppingapp.data.userDatabase.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE email=:email AND password=:password")
    fun getlogindetail(email: String, password :String): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToCart(cart: Cart)

    @Query("SELECT * from user_table where id=:id")
    fun getUserCartProducts(id:Int): LiveData<UserCart>

    @Query("DELETE FROM Cart where id=:id AND productId=:productId")
    suspend fun deleteItemFromCart(id: Int,productId: Int)
}