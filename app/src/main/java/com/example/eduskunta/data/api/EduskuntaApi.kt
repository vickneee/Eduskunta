package com.example.eduskunta.data.api

import com.example.eduskunta.data.db.entities.MemberEntity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Base URL for the API.
 */
private const val BASE_URL = "https://users.metropolia.fi/~peterh/"

/**
 * Interface for the API service.
 */
interface EduskuntaApiService {

    /**
     * Get a list of members from the API.
     */
    @GET("mps.json")
    suspend fun getMembers(): List<MemberEntity>
}

/**
 * Singleton object for the API service.
 */
object EduskuntaApi {

    /**
     * Retrofit instance.
     * @property retrofit The Retrofit instance.
     * @property RETROFIT_SERVICE The API service.
     */
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val RETROFIT_SERVICE: EduskuntaApiService by lazy {
        retrofit.create(EduskuntaApiService::class.java)
    }
}
