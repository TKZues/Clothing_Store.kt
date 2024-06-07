package com.example.myapplication.Fragment_Admin.PhieuNhap

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Data.Model.Model_Phieunhap
import com.example.myapplication.R

class Adapter_PhieuNhap(private val phieunhap: List<Model_Phieunhap>,
                        private val onClickListener: OnClickListener
) : RecyclerView.Adapter<Adapter_PhieuNhap.PhieuNhapViewHolder>() {
    class PhieuNhapViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val name_phieunhap:TextView = view.findViewById(R.id.name_phieunhap)
        val txt_day:TextView = view.findViewById(R.id.txt_day)
        val txt_nsx:TextView = view.findViewById(R.id.txt_nsx)
        val txt_thanhtien:TextView = view.findViewById(R.id.txt_thanhtien)
        val mapn:TextView = view.findViewById(R.id.mapn)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Adapter_PhieuNhap.PhieuNhapViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_phieunhap, parent, false)

        return PhieuNhapViewHolder(view)
    }

    override fun onBindViewHolder(holder: Adapter_PhieuNhap.PhieuNhapViewHolder, position: Int) {
        val values = phieunhap[position]

        holder.name_phieunhap.text = values.ghichu
        holder.mapn.text = values.mapn
        holder.txt_nsx.text = values.nsx
        holder.txt_day.text = values.ngayNhap
        holder.txt_thanhtien.text = values.thanhTien.toString()
        holder.itemView.setOnClickListener {
            onClickListener.onClick(position,values)
        }
    }

    override fun getItemCount(): Int {
       return phieunhap.size
    }

    interface OnClickListener {
        fun onClick(position: Int, model: Model_Phieunhap)
    }
}