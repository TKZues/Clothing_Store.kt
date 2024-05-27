package com.example.myapplication.Fragment.Customer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.myapplication.R

class Fragment_customer : Fragment() {
    private lateinit var btnnewcus: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_customer, container, false)

        btnnewcus = view.findViewById(R.id.button_newcus)

        btnnewcus.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.frame_layout, Fragment_customer_add()).commit();

        }

        return view;
    }

}