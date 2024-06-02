package com.example.myapplication.Fragment_User.Home

import DatabaseHelper
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Data.Model.Model_product
import com.example.myapplication.R
class Fragment_Home : Fragment() {
private lateinit var rv_categories: RecyclerView
private lateinit var rv_product: RecyclerView
private lateinit var dbHelper: DatabaseHelper
private lateinit var img_product: ImageView
private lateinit var btn_detail: Button
private lateinit var txtaccount: TextView
    private var accountId: Long = -1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.user_home, container, false)
        dbHelper = DatabaseHelper(requireContext())

        arguments?.let {
            accountId = it.getLong("account_id", -1)
        }


        rv_categories = view.findViewById(R.id.rv_categories)
        rv_product = view.findViewById(R.id.rv_product)
        txtaccount = view.findViewById(R.id.txtaccount)

        txtaccount.text = accountId.toString()


        rv_categories.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val category = dbHelper.getAllProductType()
        val adapter = Adapter_category(category)
        rv_categories.adapter =adapter

        rv_product.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val product = dbHelper.getAllproduct()
        val adapter1 = Adapter_product_user(product, object : Adapter_product_user.OnClickListener {
            override fun onClick(position: Int, model: Model_product) {
                val intent = Intent(requireContext(), Product_Detail::class.java)
//                intent.setType("image/*")
//                intent.setAction(Intent.ACTION_GET_CONTENT)
//                intent.setAction(Intent.ACTION_OPEN_DOCUMENT)
                intent.putExtra("productname", model.tensp)
                intent.putExtra("productprice", model.gia)
                intent.putExtra("imgsource", model.img)
                intent.putExtra("productid", model.masp)
                intent.putExtra("account_id", accountId)
                 startActivity(intent)
            }
        })
        rv_product.adapter = adapter1


        return view
    }

}