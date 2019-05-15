package com.healme.helper

import com.healme.model.Apotek
import com.healme.model.Obat
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

//    @GET("bins/s41pe")
//    fun getApotek(): Call<List<Apotek>>

    //https://api.npoint.io/202f75e8b77c8eb1e60c
//    @GET("bins/1d51ei")
//    fun getApotek(): Call<List<Apotek>>

    @GET("202f75e8b77c8eb1e60c")
    fun getApotek(): Call<List<Apotek>>

//    @GET("bins/h0qs6")
//    fun getObat(): Call<List<Obat>>
    @GET("fe8ae63b208e4b1543e4")
    fun getObat(): Call<List<Obat>>
}