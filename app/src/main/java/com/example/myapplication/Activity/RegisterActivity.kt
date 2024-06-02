package com.example.myapplication.Activity

import DatabaseHelper
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Data.Mode.Model_customer
import com.example.myapplication.Data.Model.Model_account
import com.example.myapplication.R

class RegisterActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextMobile: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var cirRegisterButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_register)
        dbHelper = DatabaseHelper(this)
        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextMobile = findViewById(R.id.editTextMobile)
        editTextPassword = findViewById(R.id.editTextPassword)
        cirRegisterButton = findViewById(R.id.cirRegisterButton)

        cirRegisterButton.setOnClickListener {
            addAccount();
        }

    }

    private fun addAccount() {
        val name = editTextName.text.toString()
        val email = editTextEmail.text.toString()
        val phone = editTextMobile.text.toString()
        val pass = editTextPassword.text.toString()

        val account = Model_account(name, email, phone, pass)
        val accountId = dbHelper.addAccount(account)

        if (accountId != -1L) {
            val customer = Model_customer(name, "", "", "", "", "", accountId)
            val status = dbHelper.addNameCus(customer)
            if (status != -1L) {
                Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show()
                editTextName.text.clear()
                editTextEmail.text.clear()
                editTextMobile.text.clear()
                editTextPassword.text.clear()
            } else {
                Toast.makeText(this, "Đăng ký không thành công", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Đăng ký không thành công", Toast.LENGTH_SHORT).show()
        }
    }

}