package com.example.myapplication.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.myapplication.Fragment_Admin.Customer.Fragment_customer
import com.example.myapplication.Fragment_Admin.Dashboard.Fragment_Dashboard
import com.example.myapplication.Fragment_Admin.Product.Fragment_Product
import com.example.myapplication.Fragment_Admin.ProductType.Fragment_producttype
import com.example.myapplication.Fragment_Admin.Staff.Fragment_staff
import com.example.myapplication.R
import com.google.android.material.navigation.NavigationView

class AdminActivity : AppCompatActivity() {
    lateinit var toggle : ActionBarDrawerToggle;
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var navigationView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar)
        navigationView = findViewById(R.id.navigation_view)
        setSupportActionBar(toolbar)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, Fragment_Product()).commit()
        drawerLayout.closeDrawer(GravityCompat.START)


        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_dashboard -> {
                   supportFragmentManager.beginTransaction().replace(R.id.frame_layout,
                       Fragment_Dashboard()
                   ).commit();
                    drawerLayout.closeDrawer(GravityCompat.START) // Đóng navigation drawer sau khi chọn
                    true
                }
                R.id.nav_warehouse ->{
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout, Fragment_Product()).commit();
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_customer ->{
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout, Fragment_customer()).commit();
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_staff ->{
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout, Fragment_staff()).commit()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_producttype -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout, Fragment_producttype()).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    true
                }
                R.id.nav_logout ->   {
                    val intent =  Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }

                else -> {false}
            }
        }


    }
}