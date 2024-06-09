package com.example.myapplication.Fragment_Admin.Dashboard

import DatabaseHelper
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import java.util.Calendar


class Fragment_dashboard_ngay : Fragment() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var edt_day1: EditText
    private lateinit var btnday1: Button
    private lateinit var edt_day2: EditText
    private lateinit var btnday2: Button
    private lateinit var btn_findday: Button
    private lateinit var txt_tongchingay: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_dashboard_ngay, container, false)
        edt_day1 = view.findViewById(R.id.edt_day1)
        btnday1 = view.findViewById(R.id.btnday1)
        edt_day2 = view.findViewById(R.id.edt_day2)
        btnday2 = view.findViewById(R.id.btnday2)
        btn_findday = view.findViewById(R.id.btn_findday)
        txt_tongchingay = view.findViewById(R.id.txt_tongchingay)
        dbHelper = DatabaseHelper(requireContext())

        btnday1.setOnClickListener {
            openday1();
        }
        btnday2.setOnClickListener {
            openday2();
        }
        btn_findday.setOnClickListener {
           val tongchingay =  dbHelper.tongchitheongay(edt_day1.text.toString(), edt_day2.text.toString())

            txt_tongchingay.text = tongchingay.toString()
        }
        return view
    }

    private fun openday1() {
            val c = Calendar.getInstance()
            val day = c.get(Calendar.DAY_OF_MONTH)
            val month = c.get(Calendar.MONTH)
            val year = c.get(Calendar.YEAR)

            val calander = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val txt = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
                edt_day1.setText(txt);
            }, year, month, day)
            calander.show()
    }
    private fun openday2() {
        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH)
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)

        val calander = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            val txt = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
            edt_day2.setText(txt);
        }, year, month, day)
        calander.show()
    }
}