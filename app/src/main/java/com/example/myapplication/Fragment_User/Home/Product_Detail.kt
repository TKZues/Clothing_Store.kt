package com.example.myapplication.Fragment_User.Home

import DatabaseHelper
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText

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
import com.example.myapplication.Data.Model.Model_cart
import com.example.myapplication.Data.Model.Model_product
import com.example.myapplication.Fragment_Admin.Product.Fragment_ProductAdditems
import com.example.myapplication.R
import java.io.IOException

class Product_Detail : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var txt_productname : TextView;
    private lateinit var txt_rating : TextView;
    private lateinit var txt_des : TextView;
    private lateinit var txt_productprice : TextView;
    private lateinit var img_product : ImageView;
    private lateinit var txt_idsp : TextView;
    private lateinit var imgv_back : ImageView;
    private lateinit var btn_addcart : Button;
    private lateinit var txt_quantity : EditText;
    private val REQUEST_PERMISSION_CODE = 100


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_productdetail)
        txt_productprice = findViewById(R.id.txt_productprice)
        img_product = findViewById(R.id.img_product)
        txt_productname = findViewById(R.id.txt_productname)
        txt_des = findViewById(R.id.txt_des)
        txt_idsp = findViewById(R.id.txt_idsp)
        imgv_back = findViewById(R.id.imgv_back)
        txt_quantity = findViewById(R.id.txt_quantity)
        btn_addcart = findViewById(R.id.btn_addcart)
        dbHelper = DatabaseHelper(this)
        val productname = intent.getStringExtra("productname")
        val imgUriString = intent.getStringExtra("imgsource")
        val productid = intent.getStringExtra("productid")
        val productprice = intent.getDoubleExtra("productprice", 1.0)

        txt_productname.text = productname
        txt_productprice.text = productprice.toString()
        txt_des.text = imgUriString
        txt_idsp.text = productid
        imgUriString?.let {
            Glide.with(this)
                .load(it)
                .into(img_product)
        }

        imgv_back.setOnClickListener {
            val intent = Intent(this, Fragment_Home::class.java)
            startActivity(intent)
        }
        btn_addcart.setOnClickListener(View.OnClickListener {
            addCart();
        })


    }

    private fun addCart() {
        val quantity = txt_quantity.text.toString().toDoubleOrNull()
        val productid = txt_idsp.text.toString()

        if(quantity != null && productid.isNotEmpty()){
            val cart = Model_cart(quantity, productid);
            val status = dbHelper.addCart(cart)
            if (status > -1){
                Toast.makeText(this, "Theem vao gio hang thanh cong", Toast.LENGTH_SHORT).show()
                txt_quantity.text.clear()

            }else{
                Toast.makeText(this, "Khong them vao gio hang duoc", Toast.LENGTH_SHORT).show()

            }

        }else{
            Toast.makeText(this, "điền đầy du thông tin", Toast.LENGTH_SHORT).show()
        }
    }

}

