package com.healme

import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.BottomSheetBehavior
import android.util.Log
import android.view.View
import android.widget.LinearLayout

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_apotek_location.*
import kotlinx.android.synthetic.main.bottom_sheet_apotek.*

class ApotekLocationActivity : AppCompatActivity(), OnMapReadyCallback,
        View.OnClickListener {

    override fun onClick(v: View?) {
        when(v){
            btn_bottom_sheet -> {
                if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                } else {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }
            }
            back_location ->{
                onBackPressed()
            }
        }
    }

    private lateinit var mMap: GoogleMap
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apotek_location)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        btn_bottom_sheet.setOnClickListener(this)
        btn_see_more.setOnClickListener(this)
        btn_direct.setOnClickListener(this)
        back_location.setOnClickListener(this)
        bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet_container)
        Handler().postDelayed({
            bottomSheetBehavior.peekHeight =
                    tv_apotek_name.measuredHeight + btn_bottom_sheet.measuredHeight + 375
            tv_apotek_name.text = intent.getStringExtra("nama")
            tv_apotek_location.text = intent.getStringExtra("lokasi")
            btn_direct.setOnClickListener{
                val gmmIntentUri = Uri.parse("google.navigation:q=${intent.getDoubleExtra("latitude",0.0)},${intent.getDoubleExtra("longitude",0.0)}")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            }
            btn_see_more.setOnClickListener {
                val id = intent.getIntExtra("id",0)
                val namaApotek = intent.getStringExtra("nama")
                Log.d("apotekId", "$id")
                val intent = Intent(this@ApotekLocationActivity,SeeMoreActivity::class.java)
                intent.putExtra("id", id)
                intent.putExtra("nama",namaApotek)
                startActivity(intent)
            }
        }, 1)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(intent.getDoubleExtra("latitude",0.0), intent.getDoubleExtra("longitude",0.0))
        mMap.addMarker(MarkerOptions().position(sydney).title("${intent.getStringExtra("nama")}"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap.setMinZoomPreference(16F)
    }
}
