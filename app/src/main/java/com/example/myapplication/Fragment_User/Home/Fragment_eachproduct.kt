package com.example.myapplication.Fragment_User.Home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class Fragment_eachproduct: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_product_user)
        val img_product : ImageView = findViewById(R.id.img_product)
        val btn_detail: Button = findViewById(R.id.btn_detail)
        btn_detail.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, Product_Detail::class.java)
            startActivity(intent)
        })
    }
}