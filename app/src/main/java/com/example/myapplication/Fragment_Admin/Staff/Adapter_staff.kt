package com.example.myapplication.Fragment_Admin.Staff

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Data.Model.Model_staff
import com.example.myapplication.R

class Adapter_staff(private val staffList : List<Model_staff>): RecyclerView.Adapter<Adapter_staff.StaffViewHolder>() {
    class StaffViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txt_namestaff: TextView = view.findViewById(R.id.txt_namestaff)
        val txt_addressstaff: TextView = view.findViewById(R.id.txt_addressstaff)
        val txt_workday: TextView = view.findViewById(R.id.txt_workday)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Adapter_staff.StaffViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_staff, parent, false)

        return StaffViewHolder(view)
    }

    override fun onBindViewHolder(holder: Adapter_staff.StaffViewHolder, position: Int) {
        val staff = staffList[position]
        holder.txt_namestaff.text = staff.tennv
        holder.txt_addressstaff.text = staff.dcnv
        holder.txt_workday.text = staff.ngaylam
    }

    override fun getItemCount(): Int {
        return staffList.size
    }
}