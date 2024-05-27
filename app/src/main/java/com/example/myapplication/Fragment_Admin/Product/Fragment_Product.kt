package com.example.myapplication.Fragment_Admin.Product

import DatabaseHelper
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import com.example.myapplication.Data.DatabaseHelper
import com.example.myapplication.R

class Fragment_Product : Fragment() {
    private lateinit var dbHelper: DatabaseHelper
    //private val productAdapter: AdapterProduct? = null
    //private val productList: List<Model_sanpham>? = null
    private val recyclerView: RecyclerView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment__product, container, false)

        val btnadd: Button = view.findViewById(R.id.button_add);
        btnadd.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.frame_layout, Fragment_ProductAdditems()).commit();
        }
        dbHelper = DatabaseHelper(requireContext())

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val productList = dbHelper.getAllproduct()
        val adapter = Adapter_Product(productList)
        recyclerView.adapter = adapter
        return  view;
    }


}