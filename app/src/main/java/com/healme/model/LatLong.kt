package com.healme.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Latlong(@SerializedName("latitude")
                   @Expose
                   var latitude: Double? = null,
                   @SerializedName("longitude")
                   @Expose
                   var longitude: Double? = null)