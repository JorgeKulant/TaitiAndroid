package com.example.taitiandroid.ui.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
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

class MapFragment : Fragment(R.layout.fragment_map), OnMapReadyCallback {
    private lateinit var map: GoogleMap

    companion object {
        const val REQUEST_CODE_LOCATION = 0
    }

    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

        val view = inflater.inflate(R.layout.fragment_map, container, false)
        val mapFragment = getChildFragmentManager().findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onMapReady(p0: GoogleMap) {
        map = p0
        createMarker()
        enableLocation()
    }

    private fun createMarker() {
        val coordinatesThiar = LatLng(37.857271, -0.787801)
        val markerThiar: MarkerOptions = MarkerOptions().position(coordinatesThiar).title("Taiti")
        map.addMarker(markerThiar)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinatesThiar, 16f),
            4000, null
        )
    }

    private fun isLocationPermissionGranted() = ContextCompat.checkSelfPermission(
        this.requireContext(),
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED


    @SuppressLint("MissingPermission")
    private fun enableLocation() {
        if (!::map.isInitialized) return
        if (isLocationPermissionGranted()) {
            map.isMyLocationEnabled = true
        } else {
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this.requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            Toast.makeText(this.context, "Ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT)
                .show()
        } else {
            ActivityCompat.requestPermissions(
                this.requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION
            )
        }
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_LOCATION -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                map.isMyLocationEnabled = true

            } else {
                Toast.makeText(
                    this.context,
                    "Para activar la localización, ve a ajustes y acepta los permisos",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {}
        }
    }

   @SuppressLint("MissingPermission")
   override fun onResume() {
        super.onResume()
        if (!::map.isInitialized) return
        if (!isLocationPermissionGranted()) {
            map.isMyLocationEnabled = false
            Toast.makeText(
                this.context,
                "Para activar la localización, ve a ajustes y acepta los permisos",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}
