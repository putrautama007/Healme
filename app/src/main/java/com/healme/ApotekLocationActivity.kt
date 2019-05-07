package com.healme

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.BottomSheetBehavior
import android.view.View
import android.widget.LinearLayout

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.bottom_sheet_apotek.*
import org.jetbrains.anko.startActivity

class ApotekLocationActivity : AppCompatActivity(), OnMapReadyCallback, View.OnClickListener {
    override fun onClick(v: View?) {
        when(v){
            btn_bottom_sheet -> {
                if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                } else {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }
            }
            btn_direct ->{

            }
            btn_see_more ->{
                startActivity<SeeMoreActivity>()
            }
        }
    }

    private lateinit var mMap: GoogleMap
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var param: LinearLayout.LayoutParams

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apotek_location)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        btn_bottom_sheet.setOnClickListener(this)
        btn_see_more.setOnClickListener(this)
        btn_direct.setOnClickListener(this)
        bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet_container)
        Handler().postDelayed(object : Runnable {
            override fun run() {
                bottomSheetBehavior.peekHeight =
                        tv_apotek_name.measuredHeight + btn_bottom_sheet.measuredHeight + 350


            }
        }, 1)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}
