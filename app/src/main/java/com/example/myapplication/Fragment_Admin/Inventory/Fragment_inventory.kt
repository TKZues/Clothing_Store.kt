package com.example.myapplication.Fragment_Admin.Inventory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.myapplication.R

class Fragment_inventory : Fragment() {
    private lateinit var button_addinventory: Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_inventory, container, false)
        button_addinventory = view.findViewById(R.id.button_addinventory)

        button_addinventory.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.frame_layout, Fragment_addinventory()).commit()
        }

        return view
    }
}