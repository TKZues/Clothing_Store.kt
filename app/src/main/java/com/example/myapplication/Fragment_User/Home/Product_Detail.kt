package com.example.myapplication.Fragment_User.Home

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log

import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.myapplication.Data.Model.Model_product
import com.example.myapplication.Fragment_Admin.Product.Fragment_ProductAdditems
import com.example.myapplication.R
import java.io.IOException

class Product_Detail : AppCompatActivity() {
    private lateinit var txt_productname : TextView;
    private lateinit var txt_rating : TextView;
    private lateinit var txt_des : TextView;
    private lateinit var txt_productprice : TextView;
    private lateinit var img_product : ImageView;
    private val REQUEST_PERMISSION_CODE = 100


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_productdetail) 
        txt_productprice = findViewById(R.id.txt_productprice)
        img_product = findViewById(R.id.img_product)
        txt_productname = findViewById(R.id.txt_productname)
        txt_des = findViewById(R.id.txt_des)
        val productname = intent.getStringExtra("productname")
        val imgUriString = intent.getStringExtra("imgsource")
        val productprice = intent.getDoubleExtra("productprice", 1.0)

        txt_productname.text = productname
        txt_productprice.text = productprice.toString()
        txt_des.text = imgUriString
        imgUriString?.let {
            Glide.with(this)
                .load(it)
                .into(img_product)
        }

    }

}

