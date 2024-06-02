package com.example.myapplication.Fragment_Admin.Customer

import DatabaseHelper
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.Data.Mode.Model_customer
import com.example.myapplication.R
import java.util.Calendar

class Fragment_customer_add : Fragment() {
    private  lateinit var dbHelper: DatabaseHelper
    private lateinit var txt_daycus: TextView
    private lateinit var btncalander: Button
    private lateinit var button_addcus: Button
    private lateinit var edt_namecus: EditText
    private lateinit var edt_addresscus: EditText
    private lateinit var edt_gendercus: EditText
    private lateinit var edt_phonecus: EditText
    private lateinit var edt_notecus: EditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_customer_addcustomer, container, false);
        dbHelper = DatabaseHelper(requireContext())
        btncalander = view.findViewById(R.id.button_calendar);
        txt_daycus = view.findViewById(R.id.txt_daycus)
        edt_namecus = view.findViewById(R.id.edt_namecus)
        edt_addresscus = view.findViewById(R.id.edt_addresscus)
        edt_gendercus = view.findViewById(R.id.edt_gendercus)
        edt_phonecus = view.findViewById(R.id.edt_phonecus)
        edt_notecus = view.findViewById(R.id.edt_notecus)
        button_addcus = view.findViewById(R.id.button_addcus)
        btncalander.setOnClickListener(View.OnClickListener {
            openDatePicker();
        })
        button_addcus.setOnClickListener {
            addCustomer();
        }
        return view;
    }

    private fun addCustomer() {
        val name = edt_namecus.text.toString();
        val address = edt_addresscus.text.toString();
        val gender = edt_gendercus.text.toString();
        val daycus = txt_daycus.text.toString()
        val phone = edt_phonecus.text.toString();
        val note = edt_notecus.text.toString();

        if(name.isNotEmpty() && address.isNotEmpty() && gender.isNotEmpty() && phone.isNotEmpty() && note.isNotEmpty()){
            val customer = Model_customer(name,address,daycus,gender, phone, note,1);
            val status = dbHelper.addCustomer(customer)
            if(status > -1){
                Toast.makeText(context, "Thêm nhân viên thành công", Toast.LENGTH_SHORT).show();
                edt_namecus.text.clear()
                edt_addresscus.text.clear()
                txt_daycus.setText("")
                edt_gendercus.text.clear()
                edt_phonecus.text.clear()
                edt_notecus.text.clear()
            }else{
                Toast.makeText(context, "Thêm nhân viên không thành công", Toast.LENGTH_SHORT).show()
            }

        }else {
            Toast.makeText(context, "Vui lòng nhap day du thong tin", Toast.LENGTH_SHORT).show()
        }
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