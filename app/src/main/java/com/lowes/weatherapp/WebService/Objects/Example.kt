
package com.lowes.weatherapp.WebService.Objects;
import kotlin.collections.List
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lowes.weatherapp.WebService.Objects.List as list


data class Example (
    @SerializedName("cod")
    @Expose
    val cod:String,
    @SerializedName("message")
    @Expose
    val message:Int,
    @SerializedName("cnt")
    @Expose
    val cnt:Int,
    @SerializedName("list")
    @Expose
    val list:kotlin.collections.List<list>,
    @SerializedName("city")
    @Expose
    val city:City)

