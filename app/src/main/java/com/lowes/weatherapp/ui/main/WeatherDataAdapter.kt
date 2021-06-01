package com.lowes.weatherapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lowes.weatherapp.R
import com.lowes.weatherapp.WebService.Objects.Example

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */

interface RecyClerViewClickListener{
    fun recyclerViewListClicked(v: View?, position: Int)
}
class WeatherDataAdapter(val values:Example) : RecyclerView.Adapter<WeatherDataAdapter.ViewHolder>() {
    lateinit var listener:RecyClerViewClickListener
    inner class ViewHolder(item:View):RecyclerView.ViewHolder(item),View.OnClickListener{
        val txtFeelLike:TextView
        val txtAverageTemp:TextView
        init {
            txtFeelLike= item.findViewById(R.id.txtFeelsLike)
            txtAverageTemp=item.findViewById(R.id.txtAverageTemp)
            item.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.recyclerViewListClicked(v,this.layoutPosition)
        }
    }
    fun setRecyclerListener(listener: RecyClerViewClickListener){
        this.listener=listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtFeelLike.text=values.list[position].main.temp.toString()
        holder.txtAverageTemp.text=values.list[position].weather[0].main.toString()
    }

    override fun getItemCount(): Int =values.list.size

    /*private var values:Example?=null
    override fun getCount(): Int {
        return values?.list?.size?:0
    }
    fun setValues( value:Example){
        values=value
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Any {
        TODO("Not yet implemented")
    }

    override fun hasStableIds(): Boolean {
        return true
    }
    override fun getItemId(position: Int): Long {
         values?.let{
            return it.list[position].dt.toLong()
        }
        return Long.MIN_VALUE
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        if(convertView!=null){
            val view = LayoutInflater.from(convertView.context).inflate(R.layout.fragment_item, parent, false)
            val txtFeelLike:TextView = view.findViewById(R.id.txtFeelsLike)
            val txtAverageTemp:TextView=view.findViewById(R.id.txtAverageTemp)
            values?.let{
                txtFeelLike.text=it.list[position].main.temp.toString()
                txtAverageTemp.text=it.list[position].weather[0].main.toString()
            }
            return view
        }
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.fragment_item, parent, false)
        return view
    }
*/

}