package com.example.myapplication.Fragment.Staff

import DatabaseHelper
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.Data.Model.Model_staff
import com.example.myapplication.R
import java.util.Calendar

class Fragment_staff_addstaff : Fragment() {

    private lateinit var button_calendarstaff: Button;
    private lateinit var dbHelper: DatabaseHelper;
    private lateinit var button_calendarngayvaolam: Button;
    private lateinit var button_addstaff: Button;
    private lateinit var txt_daystaff: TextView;
    private lateinit var txt_ngayvaolam: TextView;
    private lateinit var edt_namestaff: EditText;
    private lateinit var edt_addressstaff: EditText;
    private lateinit var edt_genderstaff: EditText;
    private lateinit var edt_phonestaff: EditText;
    private lateinit var edt_emailstaff: EditText;
    private lateinit var edt_department: EditText;
    private lateinit var edt_role: EditText;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_staff_addstaff, container, false)
        dbHelper = DatabaseHelper(requireContext())
        button_calendarstaff = view.findViewById(R.id.button_calendarstaff)
        button_calendarngayvaolam = view.findViewById(R.id.button_calendarngayvaolam)
        txt_daystaff = view.findViewById(R.id.txt_daystaff)
        txt_ngayvaolam = view.findViewById(R.id.txt_ngayvaolam)
        button_addstaff = view.findViewById(R.id.button_addstaff)
        edt_namestaff= view.findViewById(R.id.edt_namestaff)
        edt_addressstaff= view.findViewById(R.id.edt_addressstaff)
        edt_genderstaff= view.findViewById(R.id.edt_genderstaff)
        edt_phonestaff= view.findViewById(R.id.edt_phonestaff)
        edt_emailstaff= view.findViewById(R.id.edt_emailstaff)
        edt_department= view.findViewById(R.id.edt_department)
        edt_role= view.findViewById(R.id.edt_role)

        button_calendarstaff.setOnClickListener(View.OnClickListener {
            opencalendarstaff();
        })

        button_calendarngayvaolam.setOnClickListener(View.OnClickListener {
            opencalendarvaolam();
        })

        button_addstaff.setOnClickListener{
            addStaff();
        }


        return view;
    }

    private fun addStaff() {
        val tennv = edt_namestaff.text.toString();
        val dc = edt_addressstaff.text.toString();
        val ns = txt_daystaff.text.toString();
        val gt = edt_genderstaff.text.toString();
        val sdt = edt_phonestaff.text.toString();
        val email = edt_emailstaff.text.toString();
        val ngaylam = txt_ngayvaolam.text.toString();
        val phongban = edt_department.text.toString();
        val cvu = edt_role.text.toString();
        if(tennv.isNotEmpty() && dc.isNotEmpty()&& ns.isNotEmpty() && gt.isNotEmpty() && sdt.isNotEmpty()&& email.isNotEmpty()&& ngaylam.isNotEmpty()&& phongban.isNotEmpty()&& cvu.isNotEmpty()){
            val staff = Model_staff(tennv,gt, ns,cvu,phongban ,ngaylam,dc,sdt,email)
            val status = dbHelper.addStaff(staff)
            if(status > -1){
                Toast.makeText(context,"Them thành công",Toast.LENGTH_SHORT).show();
                edt_namestaff.text.clear();
                edt_addressstaff.text.clear();
                txt_daystaff.text = ""
                edt_genderstaff.text.clear();
                edt_phonestaff.text.clear();
                edt_emailstaff.text.clear();
                txt_ngayvaolam.text = ""
                edt_department.text.clear();
                edt_role.text.clear();
            }else{
                Toast.makeText(context, "Thêm nhân viên không thành công", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(context, "Điền đầy đủ thông tin", Toast.LENGTH_SHORT).show()
        }

    }

    private fun opencalendarvaolam() {
        val c = Calendar.getInstance();
        val day = c.get(Calendar.DAY_OF_MONTH);
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)

        val datevaolam = DatePickerDialog(requireContext(),DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            val txtvaolam = ""+dayOfMonth+ "/"+(month +1)+"/"+year;
            txt_ngayvaolam.setText(txtvaolam);
        },day,month,year);

        datevaolam.show();

    }

    private fun opencalendarstaff() {
        val c = Calendar.getInstance();
        val day = c.get(Calendar.DAY_OF_MONTH);
        val month = c.get(Calendar.MONTH);
        val year = c.get(Calendar.YEAR)

        val datepicker = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            val txtns : String = "" + dayOfMonth + "/" + (month +1) + "/" + year;
            txt_daystaff.setText(txtns);
        },day,month,year)

        datepicker.show()
    }


}