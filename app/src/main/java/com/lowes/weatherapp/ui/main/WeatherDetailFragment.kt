package com.lowes.weatherapp.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lowes.weatherapp.R
import com.lowes.weatherapp.WebService.Objects.List

private const val LIST = "list"



class WeatherDetailFragment : Fragment() {
    private var listItem: List? = null
    lateinit var txtTemp:TextView
    lateinit var txtFeelsLike:TextView
    lateinit var txtWeather:TextView
    lateinit var txtWeatherDesc:TextView


    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.let {
            listItem = it.getSerializable(LIST) as List?
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        txtTemp=view.findViewById(R.id.txtTemp)
        txtFeelsLike=view.findViewById(R.id.txtFeelsLike)
        txtWeather=view.findViewById(R.id.txtWeather)
        txtWeatherDesc=view.findViewById(R.id.txtWeatherDesc)
        listItem?.let{
            txtTemp.text=it.main.temp.toString()
            txtFeelsLike.text="Feels like: "+it.main.feelsLike.toString()
            txtWeather.text=it.weather[0].main
            txtWeatherDesc.text=it.weather[0].description
        }
        return view
    }

    companion object {

        fun newInstance(item:List?) = WeatherDetailFragment().apply {

            arguments=Bundle().apply {
                putSerializable(LIST,item)
            }

        }
    }
}