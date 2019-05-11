package com.healme.helper

import com.healme.model.Apotek
import com.healme.model.Obat
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

//    @GET("bins/s41pe")
//    fun getApotek(): Call<List<Apotek>>

    @GET("bins/948hy")
    fun getApotek(): Call<List<Apotek>>

    @GET("bins/1euq06")
    fun getObat(): Call<List<Obat>>
}