package com.example.team.Fragment

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.location.Geocoder
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.team.Fragment.model.LatLngData
import com.example.team.R
import com.example.team.data.detail
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.marker_layout.*
import java.text.NumberFormat
import java.util.*

class MapFragment : Fragment(),  OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_map, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapview) as SupportMapFragment

        mapFragment.getMapAsync(this)




        return rootView
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        for (i in detail.detail_list) {
            try {
                val marker = LatLng(i.latitude!!.toDouble(), i.longitude!!.toDouble())
                mMap.addMarker(MarkerOptions().position(marker).title(i.type))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            }
            catch (e: Exception){
                return
            }
        }

        mMap.animateCamera(CameraUpdateFactory.zoomTo(12f))
        }
}