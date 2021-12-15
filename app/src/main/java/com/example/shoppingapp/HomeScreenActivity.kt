package com.example.shoppingapp


import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.data.product.ProductRepository
import com.example.shoppingapp.data.product.ProductViewModel
import com.example.shoppingapp.data.product.ProductViewModelFactory
import com.example.shoppingapp.data.userDatabase.UserDatabase
import com.example.shoppingapp.fragments.order.OrdersFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home_screen.*
import kotlinx.android.synthetic.main.appbar_home_screen.*
import kotlinx.android.synthetic.main.content_home_screen.*
import kotlinx.android.synthetic.main.drawer_header.*

class HomeScreenActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var viewModel: ProductViewModel
    private var adapter1: RecyclerView.Adapter<ProductAdapter.ProductViewHolder>? = null
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        setUpViews()

        val dao =UserDatabase.getDatabase(applicationContext).productDao()
        val repository= ProductRepository(dao)
        viewModel=
            ViewModelProvider(this, ProductViewModelFactory(repository)).get(ProductViewModel::class.java)
        viewModel.readProduct().observe(this, Observer {
            (adapter1 as ProductAdapter).setData(it)
        })
        adapter1=ProductAdapter()
        layoutManager= LinearLayoutManager(this)

        recyclerView.layoutManager =layoutManager

        recyclerView.adapter = adapter1
        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        var header:View=drawerNavigationView.getHeaderView(0)
        var name:TextView=header.findViewById(R.id.userName)
        name.setText(sharedPref.getString("userName","Unknown User"))
        val images = listOf(
            R.drawable.banner_01,
            R.drawable.banner_02,
            R.drawable.banner_03,
            R.drawable.banner_04,
            R.drawable.banner_05,
            R.drawable.banner_06,
            R.drawable.bread_banner
        )
        val adapter= BannerPagerAdapter(images)
        viewPager.adapter = adapter
        val indicator=indicator
        indicator.setViewPager(viewPager)

    }

    fun setUpViews() {
        drawerNavigationView.setNavigationItemSelectedListener(this)
        setUpDrawerLayout()
    }
    fun setUpDrawerLayout() {
        setSupportActionBar(appBar)
        actionBarDrawerToggle = ActionBarDrawerToggle(this,mainDrawer,R.string.app_name,R.string.app_name)
        actionBarDrawerToggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){

            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var id = item.itemId
        var screenfragment : Fragment?= null
        when(id){
            R.id.feedback -> screenfragment = FeedbackFragment()
            R.id.viewCart -> Intent(this,CartActivity::class.java).also { startActivity(it) }
            R.id.logout -> AlertDialog.Builder(this).setTitle("Log Out").setMessage("Are you sure you want to Logout?")
                .setPositiveButton("Yes",DialogInterface.OnClickListener { dialog, which ->  logout()})
                .setNegativeButton("No",null)
                .show()
            R.id.your_order-> screenfragment= OrdersFragment()
            R.id.help-> screenfragment=HelpFragment()

        }
        supportFragmentManager.beginTransaction().apply {
            screenfragment?.let { replace(R.id.HomeScreenLayout, it) }
            commit()
            mainDrawer.close()
        }
        return true
    }

    private fun logout() {
        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor=sharedPref.edit()
        editor.clear()
        editor.apply()
        Intent(this,MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).also { startActivity(it) }
    }


}









