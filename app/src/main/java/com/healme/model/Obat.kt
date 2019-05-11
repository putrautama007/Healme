package com.healme.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Obat(@SerializedName("id") var id: Int? = null,
                @SerializedName("barcode")
                var barcode: Long? = null,
                @SerializedName("nama")
                var nama: String? = null,
                @SerializedName("detail")
                var detail: String? = null,
                @SerializedName("foto")
                var foto: String? = null)