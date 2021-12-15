package com.example.shoppingapp.feedback

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FeedbackDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFeedback(feedback: Feedback)

//    @Query("SELECT * FROM feedback_table ORDER BY id asc")
//    fun readAllData():LiveData<List<Feedback>>

}