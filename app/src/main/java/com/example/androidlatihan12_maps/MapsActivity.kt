package com.example.androidlatihan12_maps

import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.ActivityCompat
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback,
    GoogleMap.OnMarkerClickListener{
    override fun onMarkerClick(p0: Marker?)= false


    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient : FusedLocationProviderClient
    private lateinit var lastLocation : Location
    lateinit var text_1: TextView
    lateinit var text_2: TextView
    lateinit var text_3: TextView
    lateinit var text_4: TextView
    lateinit var fab_main : FloatingActionButton
    lateinit var fab_sub1 : FloatingActionButton
    lateinit var fab_sub2 : FloatingActionButton
    lateinit var fab_sub3 : FloatingActionButton
    lateinit var fab_sub4 : FloatingActionButton
    lateinit var fab_open : Animation
    lateinit var fab_close : Animation
    lateinit var rotate_cw : Animation
    lateinit var rotate_acw : Animation
    var isOpen : Boolean = false

    companion object {
    private const val LOCATION_PERMISSION = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        text_1 = findViewById(R.id.text_1) as TextView
        text_2 = findViewById(R.id.text_2) as TextView
        text_3 = findViewById(R.id.text_3) as TextView
        text_4 = findViewById(R.id.text_4) as TextView
        fab_main = findViewById(R.id.fab1) as FloatingActionButton
        fab_sub1 = findViewById(R.id.fab2) as FloatingActionButton
        fab_sub2 = findViewById(R.id.fab3) as FloatingActionButton
        fab_sub3 = findViewById(R.id.fab4) as FloatingActionButton
        fab_sub4 = findViewById(R.id.fab5) as FloatingActionButton
        fab_open = AnimationUtils.loadAnimation(applicationContext, R.anim.open_fab)
        fab_close = AnimationUtils.loadAnimation(applicationContext, R.anim.close_fab)
        rotate_cw = AnimationUtils.loadAnimation(applicationContext, R.anim.rotate_clockwise)
        rotate_acw = AnimationUtils.loadAnimation(applicationContext, R.anim.rotate_anticlockwise)
        fab_main.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {


                if (isOpen){
                    fab_main.setImageResource(R.drawable.gear)
                    fab_sub1.startAnimation(fab_close)
                    fab_sub2.startAnimation(fab_close)
                    fab_sub3.startAnimation(fab_close)
                    fab_sub4.startAnimation(fab_close)
                    text_1.startAnimation(fab_close)
                    text_2.startAnimation(fab_close)
                    text_3.startAnimation(fab_close)
                    text_4.startAnimation(fab_close)
                    fab_main.startAnimation(rotate_acw)
                    fab_sub1.hide()
                    fab_sub2.hide()
                    fab_sub3.hide()
                    fab_sub4.hide()
                    text_1.visibility = View.GONE
                    text_2.visibility = View.GONE
                    text_3.visibility = View.GONE
                    text_4.visibility = View.GONE
                    isOpen = false
                }else{
                    fab_sub1.startAnimation(fab_open)
                    fab_sub2.startAnimation(fab_open)
                    fab_sub3.startAnimation(fab_open)
                    fab_sub4.startAnimation(fab_open)
                    fab_main.startAnimation(rotate_cw)
                    text_1.startAnimation(fab_open)
                    text_2.startAnimation(fab_open)
                    text_3.startAnimation(fab_open)
                    text_4.startAnimation(fab_open)
                    fab_sub1.show()
                    fab_sub2.show()
                    fab_sub3.show()
                    fab_sub4.show()
                    text_1.visibility = View.VISIBLE
                    text_2.visibility = View.VISIBLE
                    text_3.visibility = View.VISIBLE
                    text_4.visibility = View.VISIBLE
                    fab_sub1.isClickable = true
                    fab_sub2.isClickable = true
                    fab_sub3.isClickable = true
                    fab_sub4.isClickable = true
                    isOpen = true
                    fab_main.setImageResource(R.drawable.gear)
                }
            }

        })
        fab_sub1.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            }

        })
        fab_sub2.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
            }

        })
        fab_sub3.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
            }

        })
        fab_sub4.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            }

        })
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
//        val sarkom = LatLng(-6.1647626, 106.7649719)
////        mMap.addMarker(MarkerOptions().position(sarkom).title("Sarang Komodo"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sarkom))
//        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
//        val markerOptions = MarkerOptions().position(sarkom)
//        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(
//            BitmapFactory.decodeResource(resources, R.mipmap.ic_car)
//        ))
//        mMap.addMarker(markerOptions.title("Sarang Komodo"))

        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)
        settingUpMaps()

    }

    private fun settingUpMaps(){
        if(ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION)
            return
        }

        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) {
            location ->
            if (location != null){
                lastLocation = location
                val currentpost = LatLng(location.latitude, location.longitude)
                placeMarkerInMaps(currentpost)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentpost, 18.0f))
            }
        }
    }
    fun placeMarkerInMaps(loc : LatLng){
        val markerOptions = MarkerOptions().position(loc)
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(
            BitmapFactory.decodeResource(resources, R.mipmap.ic_car)
        ))
        mMap.addMarker(markerOptions)
    }
}
