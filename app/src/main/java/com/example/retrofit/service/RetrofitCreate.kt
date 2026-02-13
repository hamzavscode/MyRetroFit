package com.example.retrofit.service


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitCreate {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val instance: ApiServiceCreate by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceCreate::class.java)
    }
}
