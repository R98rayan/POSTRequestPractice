package com.example.postrequestpractice

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {
    @GET("?format=json")
    fun getBooks(): Call<Books>

    @POST("?format=json")
    fun addBook(@Body bookData: BooksItem): Call<BooksItem>


}