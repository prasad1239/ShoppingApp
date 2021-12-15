package com.example.shoppingapp.feedback

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
    (tableName = "feedback_table", primaryKeys = ["id","feedback"])
data class Feedback (
    val id : Int,

    val feedback : String)
