package com.healme.helper

import com.healme.model.Apotek
import com.healme.model.Obat
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

//    @GET("bins/s41pe")
//    fun getApotek(): Call<List<Apotek>>

    @GET("bins/1dtxxi")
    fun getApotek(): Call<List<Apotek>>

    @GET("bins/h0qs6")
    fun getObat(): Call<List<Obat>>
}