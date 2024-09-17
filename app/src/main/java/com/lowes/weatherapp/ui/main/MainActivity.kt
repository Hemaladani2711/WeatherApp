package com.lowes.weatherapp.ui.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentManager
import com.lowes.weatherapp.R
import com.lowes.weatherapp.checkLocationPermission

class MainActivity : AppCompatActivity(), FragmentManager.OnBackStackChangedListener {
    private val LOCATION_PERMISSION_REQUEST_CODE = 1

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)


        if (!checkLocationPermission(this)) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE)
        } else {
            initializeApp(savedInstanceState)
        }
    }



    private fun initializeApp(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LookUpFragment.newInstance()).addToBackStack(null).commit()
        }
        shouldDisplayHomeAsUp()
    }

    private fun shouldDisplayHomeAsUp() {
        val canGoBack = supportFragmentManager.backStackEntryCount > 0
        actionBar?.setHomeButtonEnabled(canGoBack)
        actionBar?.setDisplayHomeAsUpEnabled(canGoBack)
    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return true
    }

    override fun onBackStackChanged() {
        shouldDisplayHomeAsUp()
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                initializeApp(null)
            } else {
                Toast.makeText(this, "Location permission is required", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        val fragmentCount = supportFragmentManager.backStackEntryCount
        if (fragmentCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}