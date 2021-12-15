package com.example.shoppingapp.fragments.register
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shoppingapp.R
import com.example.shoppingapp.data.userDatabase.User
import com.example.shoppingapp.data.userDatabase.UserViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_registration.*

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private lateinit var mUserViewModel : UserViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        btnRegister.setOnClickListener{
            var flag : Boolean = false
            if(eTFirst_Name.text.isEmpty()) {
                eTFirst_Name.error = "First Name can not be empty"
            flag=true}
            if(eTLast_Name.text.isEmpty()) {
                eTLast_Name.error = "Last Name can not be empty"
                flag=true}
            if(eTMobileNo.text.isEmpty()) {
                eTMobileNo.error = "Mobile Number can not be empty"
                flag=true}
            if(eTPassword.text.isEmpty()) {
                eTPassword.error = "Password can not be empty"
                flag=true}
            if(eTUser_ID.text.isEmpty()) {
                eTUser_ID.error = "User ID can not be empty"
                flag=true}
            if(flag){
                return@setOnClickListener
            }
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {

        val tuserid = eTUser_ID.text.toString()
        val tfirstname = eTFirst_Name.text.toString()
        val tlastname = eTLast_Name.text.toString()
        val tmobilenumber = eTMobileNo.text.toString()
        val tpassword = eTPassword.text.toString()

        val user = User(0,tuserid,tfirstname,tlastname,tmobilenumber,tpassword)
        mUserViewModel.addUser(user)

        Toast.makeText(requireContext(),"User Successfully Registered",Toast.LENGTH_LONG).show()

        findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)



    }



}