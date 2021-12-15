package com.example.shoppingapp.feedback

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.data.userDatabase.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeedbackViewModel (application: Application): AndroidViewModel(application){
//    private val readAllData: LiveData<List<Feedback>>
    private val repository: FeedbackRepository
    init {
        val feedbackDao = UserDatabase.getDatabase(application).feedbackDao()
        repository = FeedbackRepository(feedbackDao)
//        readAllData = repository.readAllData
    }

    fun addFeedback(feedback: Feedback){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFeedback(feedback)
        }
    }
}