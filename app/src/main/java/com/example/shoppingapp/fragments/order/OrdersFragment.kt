package com.example.shoppingapp.fragments.order
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.R
import com.example.shoppingapp.services.ApiInterface
import com.example.shoppingapp.services.ServiceBuilder
import com.example.shoppingapp.services.model.OrdersData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrdersFragment : Fragment() {
//    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    private var adapter1: RecyclerView.Adapter<OrderAdapter.OrderViewHolder>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_order, container, false)
        val retrofit = ServiceBuilder.buildService(ApiInterface::class.java)
        adapter1= OrderAdapter()
        val orderRecyclerView : RecyclerView = root.findViewById(R.id.orderRecyclerView)
        layoutManager= LinearLayoutManager(context)
        orderRecyclerView.layoutManager =layoutManager
        orderRecyclerView.adapter = adapter1
        Log.d("tag","Orders")
        retrofit.getOrders().enqueue(
            object :Callback<OrdersData>{
                override fun onResponse(
                    call: Call<OrdersData>,
                    response: Response<OrdersData>
                ) {
                    if(response.isSuccessful){
                        response.body()?.let { (adapter1 as OrderAdapter).setData(it.data) }
                    }
                    else{
                        Toast.makeText(context,"Some Error Occured",Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<OrdersData>, t: Throwable) {
                    Toast.makeText(context,"Some Error Occured",Toast.LENGTH_LONG).show()
                }
            }
        )
//        retrofit.getProducts().enqueue(
//            object : Callback<OrdersData> {
//
//                override fun onFailure(call: Call<OrdersData>, t: Throwable) {
//                    Log.d("Error", "error:  : $t)}")
//                }
//
//                override fun onResponse(call: Call<OrdersData>, response: Response<OrdersData>) {
//                    if (response.body() != null) {
//                        Log.d("Response", response.body()?.itemName.toString())
//                        Log.d("Response", response.body()?.desc.toString())
//                        Log.d("Response", response.body()?.price.toString())
//                        Log.d("Response", response.body()?.currency.toString())
//                        Log.d("Response", response.body()?.expiryDate.toString())
//                        Log.d("Response", response.body()?.quantity.toString())
////                        products = response.body()!!.data.toList()
////                        val recycleView = binding.recycleView
////                        val adapter = ProductsAdapter(products)
////                        recycleView.adapter = adapter
////                        recycleView.layoutManager = LinearLayoutManager(requireActivity())
//
//                    }
//                }
//            }
//        )
//        val repository = Repository()
//        val viewModelFactory = MainViewModelFactory(repository)
//        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
//        viewModel.getPost()
//        viewModel.myResponse.observe(viewLifecycleOwner, Observer {
//            response ->
//            if (response.isSuccessful) {
//                Log.d("Response", response.body()?.itemName.toString())
//                Log.d("Response", response.body()?.desc.toString())
//                Log.d("Response", response.body()?.price.toString())
//                Log.d("Response", response.body()?.currency.toString())
//                Log.d("Response", response.body()?.expiryDate.toString())
//                Log.d("Response", response.body()?.quantity.toString())
//
//            }
//            else
//                Log.d("Response", response.errorBody().toString())
//        })
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        var rf = Retrofit.Builder()
//            .baseUrl(RetrofitInterface.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        var API = rf.create(RetrofitInterface::class.java)
//        var call = API.posts
//        call?.enqueue(object : Callback<List<PostModel?>?>{
//            override fun onResponse(
//                call: Call<List<PostModel?>?>,
//                response: Response<List<PostModel?>?>
//            ) {
//              var postlist :List<PostModel>? = response.body() as List<PostModel>
//                var post = arrayOfNulls<String>(postlist!!.size)
//
//                for ( i in postlist!!.indices) {
//                    post[i] = postlist!![i]!!.userId.toString()
//                }
//
//                var adapter = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_dropdown_item_1line,post)
//                listView.adapter = adapter
//            }
//
//            override fun onFailure(call: Call<List<PostModel?>?>, t: Throwable) {
//
//            }
//
//        })
//    }

    }
}