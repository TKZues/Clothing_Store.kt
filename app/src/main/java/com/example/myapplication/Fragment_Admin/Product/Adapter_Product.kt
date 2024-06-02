package com.example.myapplication.Fragment_Admin.Product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.Data.Model.Model_product
import com.example.myapplication.R

class Adapter_Product(private val productList: ArrayList<Model_product>) :
    RecyclerView.Adapter<Adapter_Product.ProductHolder>() {

    class ProductHolder(viewitem: View) :RecyclerView.ViewHolder(viewitem) {
//        val tvProductID : TextView = viewitem.findViewById(R.id.tvProductID)
        val txt_tensp : TextView = viewitem.findViewById(R.id.txt_tensp)
        val txt_gia : TextView = viewitem.findViewById(R.id.txt_gia)
        val txt_theloai : TextView = viewitem.findViewById(R.id.txt_theloai)
        val txt_donvi : TextView = viewitem.findViewById(R.id.txt_donvi)
        val img_product : ImageView = viewitem.findViewById(R.id.img_product)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Adapter_Product.ProductHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_products, parent, false)

        return  ProductHolder(view);
    }



    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val product = productList[position]
//        holder.tvProductID.text  = product.masp
        holder.txt_tensp.text  = product.tensp
        holder.txt_gia.text  = product.gia.toString()
        holder.txt_theloai.text  = product.maloai
        holder.txt_donvi.text  = product.donvi
        Glide.with(holder.itemView).load(product.img).into(holder.img_product)
    }

    override fun getItemCount(): Int {
       return productList.size
    }

}

data class Product(val masp: String,val tensp: String,val gia: Double,val maloai: String,val donvi: String,val image: String)