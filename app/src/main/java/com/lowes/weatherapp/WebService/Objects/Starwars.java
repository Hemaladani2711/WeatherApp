package com.lowes.weatherapp.WebService.Objects;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Starwars {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("timestamp")
        @Expose
        private String timestamp;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("locationline1")
        @Expose
        private String locationline1;
        @SerializedName("locationline2")
        @Expose
        private String locationline2;
        @SerializedName("phone")
        @Expose
        private String phone;

}
