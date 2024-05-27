package com.example.myapplication.Fragment_Admin.Customer

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

class Fragment_customer : Fragment() {
    private lateinit var btnnewcus: Button
    private lateinit var rvcustomer: RecyclerView
    private lateinit var dbHelper: DatabaseHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_customer, container, false)
        dbHelper = DatabaseHelper(requireContext())
        btnnewcus = view.findViewById(R.id.button_newcus)
        rvcustomer = view.findViewById(R.id.rvcustomer)

        btnnewcus.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.frame_layout, Fragment_customer_add()).commit();

        }

        rvcustomer.layoutManager = LinearLayoutManager(requireContext())
        val customerlist = dbHelper.getAllCustomer()
        val adapter = Adapter_customer(customerlist)
        rvcustomer.adapter = adapter

        return view;
    }

}