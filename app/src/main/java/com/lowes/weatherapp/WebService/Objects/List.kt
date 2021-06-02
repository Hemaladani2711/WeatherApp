
package com.lowes.weatherapp.WebService.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable
import kotlin.collections.List

data class List(
    @SerializedName("dt")
    @Expose
    val dt:Int,
    @SerializedName("main")
    @Expose
    val main:Main,
    @SerializedName("weather")
    @Expose
    val weather:List<Weather>,
    @SerializedName("clouds")
    @Expose
    val clouds:Clouds,
    @SerializedName("wind")
    @Expose
    val wind:Wind,
    @SerializedName("visibility")
    @Expose
    val visibility:Int,
    @SerializedName("pop")
    @Expose
    val pop:Double,
    @SerializedName("sys")
    @Expose
    val sys:Sys,
    @SerializedName("dt_txt")
    @Expose
    val dtTxt:String,
    @SerializedName("rain")
    @Expose
    val rain:Rain):Serializable



