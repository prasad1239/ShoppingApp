package com.example.shoppingapp.data.userDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val email: String,
    val firstNamed: String,
    val lastName: String,
    val mobileNumber: String,
    val password: String
)