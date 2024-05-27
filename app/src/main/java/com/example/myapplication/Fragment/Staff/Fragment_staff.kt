package com.example.myapplication.Fragment.Staff

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.myapplication.R


class Fragment_staff : Fragment() {
    private lateinit var button_newstaff: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_staff, container, false)
        button_newstaff = view.findViewById(R.id.button_newstaff);
        button_newstaff.setOnClickListener(View.OnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.frame_layout, Fragment_staff_addstaff()).commit();
        })
        return view;
    }

}