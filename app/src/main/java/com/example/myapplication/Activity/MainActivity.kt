package com.example.myapplication.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val txtussername = findViewById<EditText>(R.id.txtusername);
        val txtpassword = findViewById<EditText>(R.id.password);
        val btnlogin = findViewById<Button>(R.id.btnlogin);
        btnlogin.setOnClickListener {
            val txtussername = txtussername.text.toString();
            val txtpassword = txtpassword.text.toString();
            if (txtussername == "admin" && txtpassword == "1"){
                val intent = Intent(this, AdminActivity::class.java)
                startActivity(intent);
            }
        }

    }
}
