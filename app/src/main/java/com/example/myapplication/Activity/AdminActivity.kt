package com.example.myapplication.Activity

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.myapplication.Fragment.Customer.Fragment_customer
import com.example.myapplication.Fragment.Dashboard.Fragment_Dashboard
import com.example.myapplication.Fragment.Product.Fragment_Product
import com.example.myapplication.Fragment.Staff.Fragment_staff
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

                else -> {false}
            }
        }


    }
}