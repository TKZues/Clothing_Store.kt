package com.example.myapplication.Fragment_Admin.PhieuNhap

import DatabaseHelper
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Data.Model.Model_Phieunhap
import com.example.myapplication.R

class Fragment_PhieuNhap : Fragment() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var btn_addphieunhap: Button
    private lateinit var rvphieunhap: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment__phieunhap, container, false)
        btn_addphieunhap = view.findViewById(R.id.btn_addphieunhap)
        rvphieunhap = view.findViewById(R.id.rvphieunhap)
        dbHelper  = DatabaseHelper(requireContext())

        btn_addphieunhap.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.frame_layout, Fragment_phieunhap_add()).commit()
        }

        rvphieunhap.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        val phieunhapList = dbHelper.getAllPhieuNhap()
        val adapter = Adapter_PhieuNhap(phieunhapList,object : Adapter_PhieuNhap.OnClickListener{
            override fun onClick(position: Int, model: Model_Phieunhap) {
                val intent = Intent(requireContext(), Fragment_ctphieunhap_add::class.java);
                intent.putExtra("mapn", model.mapn)
                startActivity(intent)
            }
        })
        rvphieunhap.adapter = adapter

        return view
    }
}