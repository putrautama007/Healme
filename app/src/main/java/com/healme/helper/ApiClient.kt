package com.healme.helper

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    fun create(): ApiInterface {
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.myjson.com/")
                .build()
        return retrofit.create(ApiInterface::class.java)
    }
}