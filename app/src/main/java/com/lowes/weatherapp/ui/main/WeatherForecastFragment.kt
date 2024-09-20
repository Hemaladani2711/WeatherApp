package com.lowes.weatherapp.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lowes.weatherapp.R
import com.lowes.weatherapp.WebService.Objects.Example


/**
 * A fragment representing a list of Items.
 */
const val CITY: String = "city"

class WeatherForecastFragment : Fragment(), RecyClerViewClickListener {
    lateinit var viewModel: MainViewModel
    lateinit var listView: RecyclerView
    lateinit var cityName: String
    lateinit var values: Example

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getString(CITY)?.let {
            cityName = it
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        listView = view.findViewById(R.id.list)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getWeatherData(cityName).observe(viewLifecycleOwner, Observer { items ->
            items?.let { items ->
                values = items
                listView.let { list ->
                    list.layoutManager = LinearLayoutManager(context)
                    val adapter = WeatherDataAdapter(items)
                    adapter.setRecyclerListener(this)
                    list.adapter = adapter
                }
            }
        })
    }

    companion object {
        fun newInstance(cityName: String) = WeatherForecastFragment().apply {
            arguments = Bundle().apply {
                putString(CITY, cityName)
            }
        }
    }

    override fun recyclerViewListClicked(v: View?, position: Int) {
        val fragment = WeatherDetailFragment.newInstance(values.list[position])
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, fragment)
            ?.addToBackStack(null)?.commit()
    }
}