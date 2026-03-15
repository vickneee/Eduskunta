package com.example.eduskunta.data.api

import com.example.eduskunta.data.db.Edustaja
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://users.metropolia.fi/~peterh/"

interface EduskuntaApiService {
    @GET("mps.json")
    suspend fun getEdustajat(): List<Edustaja>
}

object EduskuntaApi {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val RETROFIT_SERVICE: EduskuntaApiService by lazy {
        retrofit.create(EduskuntaApiService::class.java)
    }
}
