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



class Fragment_dashboard_thang : Fragment(){
    private lateinit var edt_month: EditText
    private lateinit var edt_year: EditText
    private lateinit var txt_tongchingay: TextView
    private lateinit var btn_findday: Button
    private lateinit var dbHelper: DatabaseHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard_thang, container, false)
        edt_month = view.findViewById(R.id.edt_month)
        edt_year = view.findViewById(R.id.edt_year)
        btn_findday = view.findViewById(R.id.btn_findday)
        txt_tongchingay = view.findViewById(R.id.txt_tongchingay)
        dbHelper = DatabaseHelper(requireContext())
        btn_findday.setOnClickListener {
            val month = edt_month.text.toString().toIntOrNull() ?: 0
            val year = edt_year.text.toString().toIntOrNull() ?: 0
            if (month in 1..12 && year in 2000..2200) {
                tongchitheoThangNam(month, year)
            } else {
                // Xử lý khi dữ liệu không hợp lệ
                txt_tongchingay.text = "Dữ liệu không hợp lệ"
            }
        }

        return view
    }
    private fun tongchitheoThangNam(month: Int, year: Int) {
        val tongchingay = dbHelper.tongchitheoThangNam(month, year)
        txt_tongchingay.text = tongchingay.toString()
    }
}