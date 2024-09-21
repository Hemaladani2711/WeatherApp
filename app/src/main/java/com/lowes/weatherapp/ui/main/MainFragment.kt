package com.lowes.weatherapp.ui.main

import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.lowes.weatherapp.R
import com.lowes.weatherapp.checkLocationPermission

class MainFragment : Fragment() {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var city = ""

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var btnLookUp: Button
    private lateinit var edtCity: EditText

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.look_up_fragment, container, false)
        edtCity = view.findViewById(R.id.edtCityName)
        btnLookUp = view.findViewById(R.id.btnCity)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        edtCity.setText(city)
        getLastKnownLocation()
        btnLookUp.setOnClickListener {
            if (edtCity.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "City name cannot be empty", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val fragment = WeatherForecastFragment.newInstance(edtCity.text.toString())
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, fragment)?.addToBackStack(null)?.commit()
            }
        }
    }

    //Get Last known location using GeoCoder and set result in edit text
    private fun getLastKnownLocation() {
        if (checkLocationPermission(requireContext())) {
            fusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(requireContext())
            fusedLocationProviderClient.lastLocation?.addOnSuccessListener { location ->
                location?.let {
                    val longitude = location.longitude
                    val latitude = location.latitude
                    val geocoder = Geocoder(requireContext())
                    val addresses = geocoder.getFromLocation(latitude, longitude, 1)
                    city = addresses?.get(0)?.locality.toString()
                    edtCity.setText(city)
                }
            }
        }
    }

}