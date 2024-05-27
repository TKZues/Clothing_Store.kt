package com.example.myapplication.Fragment_Admin.Customer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Data.Mode.Model_customer
import com.example.myapplication.R

class Adapter_customer(private val customerList: List<Model_customer>):
    RecyclerView.Adapter<Adapter_customer.CustomerHolder>() {
    class CustomerHolder(view : View): RecyclerView.ViewHolder(view){
        val txt_namecus: TextView = view.findViewById(R.id.txt_namecus)
        val txt_addresscus: TextView = view.findViewById(R.id.txt_addresscus)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Adapter_customer.CustomerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_customer, parent, false)

        return CustomerHolder(view)
    }

    override fun onBindViewHolder(holder: Adapter_customer.CustomerHolder, position: Int) {
        val customer = customerList[position]
        holder.txt_namecus.text = customer.namecus
        holder.txt_addresscus.text = customer.addresscus
    }

    override fun getItemCount(): Int {
        return customerList.size
    }
}