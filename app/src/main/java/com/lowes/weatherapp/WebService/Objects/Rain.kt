package com.lowes.weatherapp.WebService.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

data class Rain (
    @SerializedName("3h")
    @Expose
    val _3h:Double)


