package com.example.myapplication.Fragment_User.Home

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.myapplication.Data.Model.Model_product
import com.example.myapplication.R

class Product_Detail : ComponentActivity() {
    private lateinit var nameprouduct : TextView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_productdetail)

        nameprouduct = findViewById(R.id.txt_nameproduct)
        val productname = intent.getStringExtra("productname")

        nameprouduct.text = productname

    }
}