package com.healme.helper

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("bins/s41p")
    fun getApotek()
}