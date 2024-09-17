package com.lowes.weatherapp.ui.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lowes.weatherapp.R

class LookUpFragment : Fragment() {
    companion object {
        fun newInstance() = LookUpFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var btnLookUp:Button
    private lateinit var edtCity:EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view =inflater.inflate(R.layout.look_up_fragment, container, false)
        edtCity=view.findViewById(R.id.edtCityName)
        btnLookUp=view.findViewById(R.id.btnCity)
        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnLookUp.setOnClickListener {
            val fragment = ItemFragment.newInstance(edtCity.text.toString())
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, fragment)?.addToBackStack(null)
                ?.commit()

        }
    }


}