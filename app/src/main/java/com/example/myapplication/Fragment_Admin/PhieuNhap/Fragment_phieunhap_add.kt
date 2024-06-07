package com.example.myapplication.Fragment_Admin.PhieuNhap

import DatabaseHelper
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.Data.Model.Model_Phieunhap
import com.example.myapplication.R


class Fragment_phieunhap_add : Fragment() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var etEntryDate: EditText
    private lateinit var etManufacturer: EditText
    private lateinit var etTotalAmount: EditText
    private lateinit var etNote: EditText
    private lateinit var etid: EditText
    private lateinit var btnAddEntry: Button
    private lateinit var btnctphieunhap: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment__phieunhap_add, container, false)
        etEntryDate = view.findViewById(R.id.etEntryDate)
        etManufacturer = view.findViewById(R.id.etManufacturer)
        etTotalAmount = view.findViewById(R.id.etTotalAmount)
        etNote = view.findViewById(R.id.etNote)
        etid = view.findViewById(R.id.etid)
        btnAddEntry = view.findViewById(R.id.btnAddEntry)
        dbHelper = DatabaseHelper(requireContext())

        btnAddEntry.setOnClickListener {
            addPhieunhap();
        }

        return view
    }

    private fun addPhieunhap() {
        val ngay = etEntryDate.text.toString()
        val nsx = etManufacturer.text.toString()
        val etid = etid.text.toString()
        val thanhtien = etTotalAmount.text.toString().toDoubleOrNull()
        val ghichu = etNote.text.toString()

        if(ngay.isNotEmpty() && nsx.isNotEmpty() && thanhtien != null && ghichu.isNotEmpty() && etid.isNotEmpty()){
            val phieunhap = Model_Phieunhap(etid,ngay, nsx,ghichu,thanhtien )
            val status = dbHelper.addPhieuNhap(phieunhap)
            if(status > -1){
                Toast.makeText(context, "Thêm phiếu nhập thành công", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, "Thêm phiếu nhập không thành công", Toast.LENGTH_SHORT).show()
                etEntryDate.text.clear()
                etManufacturer.text.clear()
                etTotalAmount.text.clear()
                etNote.text.clear()
            }

        }else{
            Toast.makeText(context, "Điền đầy đủ thông tin", Toast.LENGTH_SHORT).show()
        }
    }
}