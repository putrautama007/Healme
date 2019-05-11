package com.healme.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Apotek(
        @SerializedName("id")
        @Expose
        var id: Int? = null,
        @SerializedName("nama")
        @Expose
        var nama: String? = null,
        @SerializedName("latlong")
        @Expose
        var latlong: Latlong? = null,
        @SerializedName("lokasi")
        @Expose
        var lokasi: String? = null,
        @SerializedName("foto")
        var foto: String? = null,
        @SerializedName("obat")
        @Expose
        var obat: List<Obat>? = null
)