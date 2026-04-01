package com.example.foododeringsystem
import retrofit2.Call
import retrofit2.http.GET

data class Product(
    val name: String,
    val language: String,
    val id: String,
    val bio: String,
    val version: Double
)

interface ApiService {
    @GET("Demos/json-dummy-data/64KB.json")
    fun getProducts(): Call<List<Product>>
}