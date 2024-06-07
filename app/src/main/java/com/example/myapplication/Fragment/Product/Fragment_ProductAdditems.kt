package com.example.myapplication.Fragment.Product

import DatabaseHelper
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
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
import androidx.fragment.app.Fragment
import com.example.myapplication.Data.Model.Model_product
import com.example.myapplication.R
import java.io.IOException

class Fragment_ProductAdditems : Fragment() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var edt_masp: EditText
    private lateinit var edt_tensp: EditText
    private lateinit var edt_gia: EditText
    private lateinit var edt_maloai: EditText
    private lateinit var edt_donvi: EditText
    private lateinit var imageGalleryView: ImageView
    private lateinit var edt_imageSource: EditText
    private lateinit var buttonAddProduct: Button
    private lateinit var buttonAdd: Button
    private val MY_REQUEST_CODE = 10
    private val TAG: String = Fragment_ProductAdditems::class.java.name

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
                        imageGalleryView.setImageBitmap(bitmap)
                        edt_imageSource.setText(uri.toString())
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
        val view = inflater.inflate(R.layout.fragment__product_additems, container, false)
        dbHelper = DatabaseHelper(requireContext())
        buttonAdd = view.findViewById(R.id.btn_selectimage)
        buttonAddProduct = view.findViewById(R.id.button_addproduct)
        edt_masp = view.findViewById(R.id.edt_masp)
        edt_tensp = view.findViewById(R.id.edt_tensp)
        edt_gia = view.findViewById(R.id.edt_gia)
        edt_maloai = view.findViewById(R.id.edt_maloai)
        edt_donvi = view.findViewById(R.id.edt_donvi)
        edt_imageSource = view.findViewById(R.id.edt_image)
        imageGalleryView = view.findViewById(R.id.image_gallery)

        buttonAdd.setOnClickListener {
            onClickRequestPermission()
        }
        buttonAddProduct.setOnClickListener {
            addProduct()
        }
        return view
    }

    private fun addProduct() {
        val masp = edt_masp.text.toString()
        val tensp = edt_tensp.text.toString()
        val gia = edt_gia.text.toString().toDoubleOrNull()
        val maloai = edt_maloai.text.toString()
        val donvi = edt_donvi.text.toString()
        val image = edt_imageSource.text.toString()

        if (masp.isNotEmpty() && tensp.isNotEmpty() && gia != null && maloai.isNotEmpty() && donvi.isNotEmpty() && image.isNotEmpty()) {
            val product = Model_product(masp, tensp, gia, maloai, donvi, image)
            val status = dbHelper.addProduct(product)
            if (status > -1) {
                Toast.makeText(context, "Product added successfully!", Toast.LENGTH_SHORT).show()
                edt_masp.text.clear()
                edt_tensp.text.clear()
                edt_gia.text.clear()
                edt_maloai.text.clear()
                edt_donvi.text.clear()
                edt_imageSource.text.clear()
            } else {
                Toast.makeText(context, "Failed to add product", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
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
}
