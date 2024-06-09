package com.example.myapplication.Fragment_Admin.Dashboard

import DatabaseHelper
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.example.myapplication.R


class Fragment_Dashboard : Fragment() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var txt_tongdoanhthu: TextView
    private lateinit var txt_tongchi: TextView
    private lateinit var fragment1: TextView
    private lateinit var fragment2: TextView
    private lateinit var fragment3: TextView
    private lateinit var fragment4: TextView
    private lateinit var txttongsp: TextView
    private lateinit var txttonkho: TextView
    private lateinit var fragment_container: FrameLayout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment__dashboard, container, false)
        txt_tongdoanhthu = view.findViewById(R.id.txt_tongdoanhthu)
        txt_tongchi = view.findViewById(R.id.txt_tongchi)
        fragment1 = view.findViewById(R.id.fragment1)
        fragment2 = view.findViewById(R.id.fragment2)
        fragment3 = view.findViewById(R.id.fragment3)
        fragment4 = view.findViewById(R.id.fragment4)
        txttongsp = view.findViewById(R.id.txttongsp)
        txttonkho = view.findViewById(R.id.txttonkho)
        fragment_container = view.findViewById(R.id.fragment_container)
        dbHelper = DatabaseHelper(requireContext())

        val tongdoanhthu = dbHelper.tongdoanhthu()
        val tongchi = dbHelper.tongchi()
        val tongsp = dbHelper.tongsanpham()
        val tongton = dbHelper.tongtonkho()
        txttonkho.text = tongton.toString()
        txttongsp.text = tongsp.toString()
        txt_tongdoanhthu.text = tongdoanhthu.toString()
        txt_tongchi.text = tongchi.toString()

        fragment1.setOnClickListener(View.OnClickListener {
            loadFragment(Fragment_dashboard_ngay())
        })

        fragment2.setOnClickListener(View.OnClickListener {
            loadFragment(Fragment_dashboard_thang())
        })

        fragment3.setOnClickListener(View.OnClickListener {
            loadFragment(Fragment_dashboard_quy())
        })

        fragment4.setOnClickListener(View.OnClickListener {
            loadFragment(Fragment_dashboard_nam())
        })

        return view
    }

    private fun loadFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit()
    }
}