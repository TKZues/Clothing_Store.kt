package com.example.myapplication.Fragment_Admin.Inventory

import DatabaseHelper
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.Data.Model.Model_inventory
import com.example.myapplication.R

class Fragment_addinventory:Fragment() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var productid: EditText
    private lateinit var edt_quantity: EditText
    private lateinit var button_addinventory: Button
    private lateinit var button_cancel: Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view =  inflater.inflate(R.layout.fragment_inventory_add, container, false)
        productid = view.findViewById(R.id.productid)
        edt_quantity = view.findViewById(R.id.edt_quantity)
        button_addinventory = view.findViewById(R.id.button_addinventory)
        button_cancel = view.findViewById(R.id.button_cancel)
        dbHelper = DatabaseHelper(requireContext())

        button_addinventory.setOnClickListener {
            addinventory();
        }
        return view
    }

    private fun addinventory() {
        val productid = productid.text.toString().toLongOrNull()
        val edt_quantity = edt_quantity.text.toString().toIntOrNull()

        if(productid != null && edt_quantity != null){
            val inventory = Model_inventory(productid,edt_quantity);
            val status = dbHelper.addInventory(inventory)
            if(status > -1){
                Toast.makeText(context, "Thêm tồn kho thành công", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, "Thêm tồn kho không thành công", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(context, "Hãy nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
        }
    }
}