package com.example.myapplication.Fragment_Admin.Dashboard

import DatabaseHelper
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.myapplication.R


class Fragment_dashboard_quy : Fragment() {
    private lateinit var edt_quy: EditText
    private lateinit var edt_nam: EditText
    private lateinit var txt_tongchiquy: TextView
    private lateinit var btn_quy: Button
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_dashboard_quy, container, false)
        edt_quy = view.findViewById(R.id.edt_quy)
        edt_nam = view.findViewById(R.id.edt_nam)
        btn_quy = view.findViewById(R.id.btn_quy)
        txt_tongchiquy = view.findViewById(R.id.txt_tongchiquy)
        dbHelper = DatabaseHelper(requireContext())

        btn_quy.setOnClickListener {
            val quy = edt_quy.text.toString().toIntOrNull()?: 0
            val nam = edt_nam.text.toString().toIntOrNull()?:0
            if (quy in 1..4 && nam in 2000..2200) {
                tongchitheoquy(quy, nam)
            } else {
                // Xử lý khi dữ liệu không hợp lệ
                txt_tongchiquy.text = "Dữ liệu không hợp lệ"
            }
        }

        return view
    }

    private fun tongchitheoquy(quy: Int, nam: Int) {
        val tongchiquy = dbHelper.tongchitheoQuyNam(quy, nam)

        txt_tongchiquy.text = tongchiquy.toString()

    }


}