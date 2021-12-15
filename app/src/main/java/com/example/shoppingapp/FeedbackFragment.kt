package com.example.shoppingapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.shoppingapp.R
import com.example.shoppingapp.feedback.Feedback
import com.example.shoppingapp.feedback.FeedbackViewModel
import kotlinx.android.synthetic.main.fragment_feedback.*
import kotlinx.android.synthetic.main.fragment_feedback.view.*
import kotlinx.android.synthetic.main.fragment_registration.*

class FeedbackFragment : Fragment() {
    private lateinit var mFeedbackViewModel: FeedbackViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_feedback, container, false)

        mFeedbackViewModel = ViewModelProvider(this).get(FeedbackViewModel::class.java)

        view.btnFeedback.setOnClickListener {
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {
        val fback = eTFeedback.text.toString()
        val sharedPref = this.activity?.getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val userId: Int? = sharedPref?.getInt("userId", 0)
        val feedback = userId?.let { Feedback(it, fback) }
        if (feedback != null) {
            mFeedbackViewModel.addFeedback(feedback)
        }
    }
}