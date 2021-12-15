package com.example.shoppingapp.fragments.login


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shoppingapp.R
import com.example.shoppingapp.data.product.ProductEntity
import com.example.shoppingapp.data.product.ProductRepository
import com.example.shoppingapp.data.product.ProductViewModel
import com.example.shoppingapp.data.product.ProductViewModelFactory
import com.example.shoppingapp.data.userDatabase.User
import com.example.shoppingapp.data.userDatabase.UserDatabase
import com.example.shoppingapp.data.userDatabase.UserViewModel
import com.example.shoppingapp.services.ApiInterface
import com.example.shoppingapp.services.ServiceBuilder
import com.example.shoppingapp.services.model.Products
import com.example.shoppingapp.services.model.ProductsData
import com.example.shoppingapp.utils.UtilityClass
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_login.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.stream.Collectors

class LoginFragment : Fragment() {
    private lateinit var mUserViewModel: UserViewModel

    val retrofit = ServiceBuilder.buildService(ApiInterface::class.java)
    private lateinit var productViewModel: ProductViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val dao = context?.let { UserDatabase.getDatabase(it).productDao() }
        val repository= dao?.let { ProductRepository(it) }
        productViewModel=
            ViewModelProvider(this, ProductViewModelFactory(repository!!)).get(ProductViewModel::class.java)
        view.btnSign_UP.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
//        view.btnCrash.setOnClickListener {
//            throw RuntimeException("Test Crash") // Force a crash
//        }
        view.btnLogin.setOnClickListener {

            mUserViewModel.getuser(
                view.etUser_ID.text.toString(),
                view.etPassword.text.toString()
            ).observe(viewLifecycleOwner,
                Observer { t -> processLogin(t, view) })
        }
        return view
    }

    fun processLogin(t: List<User>, view: View) {
        Log.d("T ki value", t.size.toString())
        if (t.size > 0) {
            if (view.etPassword.text.toString().equals(t.get(0).password)) {
                //SharedPreference logic to store user session
                val sharedPref = this.context?.getSharedPreferences("myPref", Context.MODE_PRIVATE)
                val editor=sharedPref?.edit()
                val islogin: Boolean = true
                editor?.putBoolean("isLoggedIn",islogin)
                editor?.putString("userName",t.get(0).firstNamed+" "+t.get(0).lastName)
                editor?.putInt("userId",t.get(0).id)
                editor?.apply()
                Log.d("messsage","homescreen activity")
                insertDataToDatabase()
            }
            else{
                view.etPassword.error = "Invalid Password"
            }

        } else {
            Toast.makeText(requireContext(), "Invalid User/Password", Toast.LENGTH_LONG).show()
        }
    }

    private fun insertDataToDatabase() {
        retrofit.getProducts().enqueue(object :Callback<ProductsData>{
            override fun onResponse(call: Call<ProductsData>, response: Response<ProductsData>)
            { Log.d("reponse",Gson().toJson(response.body()))
                if(response.isSuccessful){
                    Log.d("reponse",Gson().toJson(response.body()))
                    response.body()?.let {
                        getDataInProductEntity(it.data)?.let {
                            productViewModel.insertProduct(
                                it
                            )
                        }
                    }
                    findNavController().navigate(R.id.action_loginFragment_to_homeScreenActivity)
                }
                else{
                    Toast.makeText(context,"Some Error Occured",Toast.LENGTH_LONG).show()
                }

            }

            override fun onFailure(call: Call<ProductsData>, t: Throwable) {
                Log.d("message",t.localizedMessage)
                Toast.makeText(context,"Some Error Occured",Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun getDataInProductEntity(products:List<Products>): List<ProductEntity>? {
        return products.stream().map { data->
            UtilityClass().getImage(data.itemName)?.let {
                ProductEntity(productname = data.itemName,
                    productdetail = data.desc,
                    productprice = data.price,
                    currency =data.currency,
                    expiryDate = data.expiryDate,
                    productImage = it
                )
            }
        }.collect(Collectors.toList())
    }
}
