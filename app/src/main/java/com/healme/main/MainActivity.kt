package com.healme.main

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.healme.ApotekListActivity
import com.healme.R
import com.healme.fragment.SearchFragment
import com.healme.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import android.content.pm.PackageManager
import android.os.Build
import android.app.Activity


class MainActivity : AppCompatActivity() {
    private var prevMenuItem: MenuItem? = null
    private val CAMERA_REQUEST = 1888
    private val MY_CAMERA_PERMISSION_CODE = 100


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(HomeFragment())

        bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> loadFragment(HomeFragment())
                R.id.search -> loadFragment(SearchFragment())
                R.id.scan -> scanning()
            }
            true
        }
    }

    fun scanning() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), MY_CAMERA_PERMISSION_CODE)
            }
        }else{
            val cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, CAMERA_REQUEST)
        }
//        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            requestPermissions(arrayOf(Manifest.permission.CAMERA), MY_CAMERA_PERMISSION_CODE)
//        } else {
//            val cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
//            startActivityForResult(cameraIntent, CAMERA_REQUEST)
//        }
    }

    fun barcodeScanner(bitmap: Bitmap) {
        val options = FirebaseVisionBarcodeDetectorOptions.Builder()
                .setBarcodeFormats(
                        FirebaseVisionBarcode.FORMAT_ALL_FORMATS)
                .build()
        val detector = FirebaseVision.getInstance().getVisionBarcodeDetector(options)
        val bit = bitmap.copy(Bitmap.Config.ARGB_8888, true)
        val image = FirebaseVisionImage.fromBitmap(bit)
        detector.detectInImage(image)
                .addOnSuccessListener {
                    for (firebaseBarcode in it) {

//                        coba.text = firebaseBarcode.displayValue //Display contents inside the barcode
                        Log.d("displayvalue", firebaseBarcode.displayValue)
                        if (firebaseBarcode.displayValue != null) {
                            if (firebaseBarcode.displayValue!!.isNotEmpty()) {
                                val intent = Intent(this, ApotekListActivity::class.java)
                                intent.putExtra("kode_obat", firebaseBarcode.displayValue)
                                startActivity(intent)
                            } else {
                                Toast.makeText(this, "Tidak bisa memindai barcode", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(this, "Tidak bisa memindai barcode", Toast.LENGTH_SHORT).show()
                        }

                        when (firebaseBarcode.valueType) {
                            //Handle the URL here
                            FirebaseVisionBarcode.TYPE_URL -> {
                                firebaseBarcode.url
                                Log.d("barcode", firebaseBarcode.url.toString())
                            }
                            // Handle the contact info here, i.e. address, name, phone, etc.
                            FirebaseVisionBarcode.TYPE_CONTACT_INFO -> {
                                firebaseBarcode.contactInfo
                                Log.d("barcode", firebaseBarcode.contactInfo.toString())
                            }
                            // Handle the wifi here, i.e. firebaseBarcode.wifi.ssid, etc.
                            FirebaseVisionBarcode.TYPE_WIFI -> {
                                firebaseBarcode.wifi
                                Log.d("barcode", firebaseBarcode.wifi.toString())
                            }
                            //Handle more type of Barcodes
                            FirebaseVisionBarcode.TYPE_PRODUCT -> {
                                firebaseBarcode.rawValue
                                Log.d("barcode", firebaseBarcode.rawValue)
                            }
                        }

                    }
                }
                .addOnFailureListener {
                    it.printStackTrace()
                    Toast.makeText(baseContext, "Sorry, something went wrong!", Toast.LENGTH_SHORT).show()
                }
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit()
            return true
        }
        return false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === CAMERA_REQUEST && resultCode === Activity.RESULT_OK) {
            val photo = data?.getExtras()?.get("data") as Bitmap
            if (photo != null) {
                barcodeScanner(photo)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode === MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] === PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show()
                val cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, CAMERA_REQUEST)
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show()
            }
        }
    }
}
