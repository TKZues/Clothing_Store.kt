package com.example.myapplication.Fragment_Admin.ProductType

import DatabaseHelper
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class Fragment_producttype : Fragment() {
    private lateinit var rvproducttype: RecyclerView
    private lateinit var dbHelper: DatabaseHelper
    private  lateinit var button_addproducttype: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment__producttype, container, false)
        dbHelper = DatabaseHelper(requireContext())
        button_addproducttype = view.findViewById(R.id.button_addproducttype)
        rvproducttype = view.findViewById(R.id.rvproducttype)

        button_addproducttype.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.frame_layout, Fragment_Producttype_Addtype()).commit()
        }

        rvproducttype.layoutManager = LinearLayoutManager(requireContext())
        val producttypeList = dbHelper.getAllProductType()
        val adapter = Adapter_producttype(producttypeList)
        rvproducttype.adapter = adapter


        return view;
    }
}