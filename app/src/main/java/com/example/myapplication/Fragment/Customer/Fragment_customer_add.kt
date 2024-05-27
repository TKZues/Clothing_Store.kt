package com.example.myapplication.Fragment.Customer

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import java.util.Calendar

class Fragment_customer_add : Fragment() {
    private lateinit var txt_daycus: TextView
    private lateinit var btncalander: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_customer_addcustomer, container, false);
        btncalander = view.findViewById(R.id.button_calendar);
        txt_daycus = view.findViewById(R.id.txt_daycus)
        btncalander.setOnClickListener(View.OnClickListener {
            openDatePicker();
        })
        return view;
    }

    private fun openDatePicker() {
        val c = Calendar.getInstance();
        val day = c.get(Calendar.DAY_OF_MONTH);
        val month = c.get(Calendar.MONTH);
        val year = c.get(Calendar.YEAR);

        val datepicker = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            val selectcalander: String = ""+ dayOfMonth + "/" + (month+1) + "/" +year;
            txt_daycus.setText(selectcalander);
        }, year,month, day)
        datepicker.show();
    }

}