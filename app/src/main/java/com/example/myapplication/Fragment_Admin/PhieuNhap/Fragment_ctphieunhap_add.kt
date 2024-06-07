package com.example.myapplication.Fragment_Admin.PhieuNhap

import DatabaseHelper
import android.graphics.ColorSpace.Model
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Data.Model.Model_CTPhieuNhap
import com.example.myapplication.R
class Fragment_ctphieunhap_add : AppCompatActivity() {
    private  lateinit var dbHelper: DatabaseHelper
    private lateinit var txtmapn:TextView
    private lateinit var etProductCode:TextView
    private lateinit var etQuantity:TextView
    private lateinit var etUnitPrice:TextView
    private lateinit var etTotal:TextView
    private lateinit var btnAddEntryDetail:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_ctphieunhap_add)

        txtmapn = findViewById(R.id.txtmapn)
        etProductCode = findViewById(R.id.etProductCode)
        etQuantity = findViewById(R.id.etQuantity)
        etUnitPrice = findViewById(R.id.etUnitPrice)
        etTotal = findViewById(R.id.etTotal)
        btnAddEntryDetail = findViewById(R.id.btnAddEntryDetail)
        dbHelper = DatabaseHelper(this)

        val mapn = intent.getStringExtra("mapn")
        txtmapn.text = mapn

        btnAddEntryDetail.setOnClickListener {
            addCtphieunhap();
        }
    }

    private fun addCtphieunhap() {
        val mapn = txtmapn.text.toString()
        val masp = etProductCode.text.toString()
        val soluong = etQuantity.text.toString().toIntOrNull()
        val dongia = etUnitPrice.text.toString().toDoubleOrNull()
        val tongtien = etTotal.text.toString().toDoubleOrNull()

        if(mapn.isNotEmpty() && masp.isNotEmpty() && soluong != null && dongia != null && tongtien != null ){
            val ctpn = Model_CTPhieuNhap(soluong, dongia,tongtien,masp,mapn )
            val status = dbHelper.addChiTietNhap(ctpn)
            if(status > -1){
                Toast.makeText(this, "Thêm ct phiếu nhập thành công", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Thêm ct phiếu nhập  không thành công", Toast.LENGTH_SHORT).show()
            }

        }else{
            Toast.makeText(this, "Điền đayà đủ thông tin", Toast.LENGTH_SHORT).show()
        }

    }
}