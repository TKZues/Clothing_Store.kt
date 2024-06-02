package com.example.myapplication.Activity

import DatabaseHelper
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHelper = DatabaseHelper(this)
        val txtussername = findViewById<EditText>(R.id.txtussername);
        val txtpassword = findViewById<EditText>(R.id.txtpassword);
        val btnlogin = findViewById<Button>(R.id.btnlogin);
        btnlogin.setOnClickListener {
            val txtussername = txtussername.text.toString();
            val txtpassword = txtpassword.text.toString();
            val accountId = dbHelper.getAccountIdByEmail(txtussername)
            if (txtussername == "admin" && txtpassword == "1"){
                val intent = Intent(this, AdminActivity::class.java)

                startActivity(intent);
            }
            else  if(dbHelper.checkUser(txtussername, txtpassword)) {
                val intent = Intent(this, UserActivity::class.java)
                intent.putExtra("account_id", accountId)
                startActivity(intent);
            }else  {
                Toast.makeText(this, "Tài khoaản mật khẩu khng đúng", Toast.LENGTH_SHORT).show()
            }
        }

    }
    fun onLoginClick(view : View){
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}
