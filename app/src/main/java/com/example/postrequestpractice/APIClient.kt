package com.example.postrequestpractice

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit?{
        retrofit = Retrofit.Builder()
            .baseUrl("https://dojo-recipes.herokuapp.com/test/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
}