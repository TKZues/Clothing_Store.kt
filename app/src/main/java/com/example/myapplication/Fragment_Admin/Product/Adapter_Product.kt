package com.example.myapplication.Fragment_Admin.Product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Data.Model.Model_product
import com.example.myapplication.R

class Adapter_Product(private val productList: ArrayList<Model_product>) :
    RecyclerView.Adapter<Adapter_Product.ProductHolder>() {

    class ProductHolder(viewitem: View) :RecyclerView.ViewHolder(viewitem) {
        val tvProductID : TextView = viewitem.findViewById(R.id.tvProductID)
        val tvProductName : TextView = viewitem.findViewById(R.id.tvProductName)
        val tvProductPrice : TextView = viewitem.findViewById(R.id.tvProductPrice)
        val tvProductCategory : TextView = viewitem.findViewById(R.id.tvProductCategory)
        val tvProductUnit : TextView = viewitem.findViewById(R.id.tvProductUnit)
//        val tvProductID : TextView = viewitem.findViewById(R.id.tvProductID)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Adapter_Product.ProductHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_products, parent, false)

        return  ProductHolder(view);
    }



    override fun onBindViewHolder(holder: Adapter_Product.ProductHolder, position: Int) {
        val product = productList[position]
        holder.tvProductID.text  = product.masp
        holder.tvProductName.text  = product.tensp
        holder.tvProductPrice.text  = product.gia.toString()
        holder.tvProductCategory.text  = product.maloai
        holder.tvProductUnit.text  = product.donvi
//        holder.tvProductID.text  = product.masp
    }

    override fun getItemCount(): Int {
       return productList.size
    }

}

data class Product(val masp: String,val tensp: String,val gia: Double,val maloai: String,val donvi: String,val image: String)