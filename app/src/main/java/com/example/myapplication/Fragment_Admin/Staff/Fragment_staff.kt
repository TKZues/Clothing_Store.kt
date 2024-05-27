package com.example.myapplication.Fragment_Admin.Staff

import DatabaseHelper
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R


class Fragment_staff : Fragment() {
    private lateinit var dbhelper:DatabaseHelper
    private lateinit var button_newstaff: Button
    private lateinit var ryr_staff: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_staff, container, false)
        dbhelper = DatabaseHelper(requireContext())
        button_newstaff = view.findViewById(R.id.button_newstaff);
        ryr_staff = view.findViewById(R.id.ryr_staff)
        button_newstaff.setOnClickListener(View.OnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.frame_layout, Fragment_staff_addstaff()).commit();
        })

        ryr_staff.layoutManager = LinearLayoutManager(requireContext())
        val staffList = dbhelper.getAllStaff()
        val adapter = Adapter_staff(staffList)
        ryr_staff.adapter = adapter
        return view;
    }

}