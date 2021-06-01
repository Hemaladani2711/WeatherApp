package com.lowes.weatherapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.lowes.weatherapp.R

class MainActivity : AppCompatActivity(),FragmentManager.OnBackStackChangedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LookUpFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }
        shouldDisplayHomeAsUp()
    }
    fun shouldDisplayHomeAsUp(){
        val canGoBack=supportFragmentManager.backStackEntryCount>0
        actionBar?.setHomeButtonEnabled(canGoBack)
        actionBar?.setDisplayHomeAsUpEnabled(canGoBack);

    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return true
    }

    override fun onBackStackChanged() {
        shouldDisplayHomeAsUp()
    }
}