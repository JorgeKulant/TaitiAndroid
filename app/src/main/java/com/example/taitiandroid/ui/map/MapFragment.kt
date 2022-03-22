package com.example.taitiandroid.ui.map

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.taitiandroid.R
import com.example.taitiandroid.databinding.FragmentMapBinding

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import com.google.android.gms.maps.SupportMapFragment as SupportMapFragment

class MapFragment : Fragment(R.layout.fragment_map) , OnMapReadyCallback {
    private lateinit var map: GoogleMap
    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        //_binding = FragmentMapBinding.inflate(inflater, container, false)
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        val mapFragment = getChildFragmentManager().findFragmentById(R.id.map) as SupportMapFragment
//        val mapFragment = (SupportMapFragment) getChildFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        //return binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
    //TODO mejorar el mapa para que funcione con marcadores y nuestra localización
    override fun onMapReady(p0: GoogleMap) {
        print("map ready")
        map=p0
        createMarker()

    }

    private fun createMarker(){
        print("starting marker")
        val coordinatesThiar=LatLng(37.857271, -0.787801)
        val markerThiar: MarkerOptions=MarkerOptions().position(coordinatesThiar).title("Taiti")
        map.addMarker(markerThiar)
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinatesThiar, 18f),
            4000,null)
    }

    private fun isLocationPermissionGranted(){
        ContextCompat.checkSelfPermission(this.requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED
    }
}