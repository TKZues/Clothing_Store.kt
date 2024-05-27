package com.example.myapplication.Fragment_Admin.ProductType

import DatabaseHelper
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.myapplication.Data.Model.Model_producttype
import com.example.myapplication.R
import java.io.IOException

class Fragment_Producttype_Addtype : Fragment() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var edt_producttype : EditText
    private lateinit var edt_imgproducttype : EditText
    private lateinit var btn_addproducttype : Button
    private lateinit var btn_openfile : Button
    private lateinit var img_producttype : ImageView
    private val MY_REQUEST_CODE = 10
    private val TAG: String = Fragment_Producttype_Addtype::class.java.name

    private val mActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback { result: ActivityResult ->
            Log.e(TAG, "onActivityResult")
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                val uri = data?.data
                try {
                    if (uri != null) {
                        val bitmap = MediaStore.Images.Media.getBitmap(
                            requireContext().contentResolver, uri
                        )
                        img_producttype.setImageBitmap(bitmap)
                        edt_imgproducttype.setText(uri.toString())
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment__producttype__addtype, container, false)
        edt_producttype = view.findViewById(R.id.edt_producttype)
        edt_imgproducttype = view.findViewById(R.id.edt_imgproducttype)
        btn_addproducttype = view.findViewById(R.id.btn_addproducttype)
        btn_openfile = view.findViewById(R.id.btn_openfile)
        img_producttype = view.findViewById(R.id.img_producttype)
        btn_openfile.setOnClickListener {
            onClickRequestPermission()
        }

        dbHelper = DatabaseHelper(requireContext())
        btn_addproducttype.setOnClickListener {
            addLoaisp();
        }
        return view
    }

    private fun onClickRequestPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            openGallery()
            return
        }
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            openGallery()
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                MY_REQUEST_CODE
            )
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery()
            } else {
                Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        mActivityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"))
    }

    private fun addLoaisp() {
        val tenloaisp = edt_producttype.text.toString()
        val imglsp = edt_imgproducttype.text.toString()
        if(tenloaisp.isNotEmpty()){
            val producttype = Model_producttype(tenloaisp, imglsp)
            val status = dbHelper.addLoaiSP(producttype)
            if(status > -1){
                Toast.makeText(context, "THêm loại sản phẩm thành công", Toast.LENGTH_SHORT).show();
                edt_producttype.text.clear()
            }else{
                Toast.makeText(context, "Thêm sản phẩm không thành công", Toast.LENGTH_SHORT).show()
            }

        }else{
            Toast.makeText(context,"VUi lòng nhậd day du thong tin", Toast.LENGTH_SHORT).show()
        }
    }

}