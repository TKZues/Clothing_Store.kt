package com.example.myapplication.Activity

import DatabaseHelper
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Fragment_User.Cart.Fragment_cart
import com.example.myapplication.Fragment_User.Home.Fragment_Home
import com.example.myapplication.Fragment_User.Profile.Fragment_profile
import com.example.myapplication.Fragment_User.Wishlist.Fragment_wishlist
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityUserBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class UserActivity : AppCompatActivity() {
    private lateinit var navigation_bottom: BottomNavigationView
    private lateinit var rv_categories: RecyclerView
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var binding: ActivityUserBinding
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?,) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
      navigation_bottom = findViewById(R.id.navigation_bottom)
        navigation_bottom.setOnNavigationItemSelectedListener {
            var selectedFragment : Fragment? =
                when(it.itemId){
                    R.id.nav_explore -> Fragment_Home()
                    R.id.nav_wishlist -> Fragment_wishlist()
                    R.id.nav_cart -> Fragment_cart()
                    R.id.nav_profile -> Fragment_profile()
                    else -> null
                }
            if (selectedFragment !=null){
                supportFragmentManager.beginTransaction().replace(R.id.frame, selectedFragment).commit()
            }
            true
        }
    }

}