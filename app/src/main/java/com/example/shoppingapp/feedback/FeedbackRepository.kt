package com.example.shoppingapp.feedback

import androidx.lifecycle.LiveData

class FeedbackRepository (private val feedbackDao: FeedbackDao){
//    val readAllData: LiveData<List<Feedback>> = feedbackDao.readAllData()
   suspend fun addFeedback(feedback: Feedback){
        feedbackDao.addFeedback(feedback)
    }
}