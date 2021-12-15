package com.example.shoppingapp


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.navigation.NavHostController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.fragment_login.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref = getSharedPreferences("myPref",Context.MODE_PRIVATE)
        val islogin = sharedPref.getBoolean("isLoggedIn",false)
        Log.d("shared",islogin.toString())
        if (islogin){
            val intent : Intent = Intent(this,HomeScreenActivity::class.java)
            startActivity(intent)
        }

    }

}