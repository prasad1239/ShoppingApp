package com.example.shoppingapp.data.userDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoppingapp.data.cartDatabase.Cart
import com.example.shoppingapp.data.product.ProductDao
import com.example.shoppingapp.data.product.ProductEntity
import com.example.shoppingapp.feedback.Feedback
import com.example.shoppingapp.feedback.FeedbackDao

@Database(entities = [User::class, ProductEntity::class,Cart::class,Feedback::class ],version = 1,exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao
    abstract fun productDao(): ProductDao
    abstract fun feedbackDao(): FeedbackDao

    companion object{

        private  var INSTANCE : UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {

            val tempInstance = INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this){

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}