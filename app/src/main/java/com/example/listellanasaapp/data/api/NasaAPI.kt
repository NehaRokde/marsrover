package com.example.listellanasaapp.data.api

import com.example.listellanasaapp.BuildConfig
import com.example.listellanasaapp.data.api.model.APODResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaAPI {

    @GET("/planetary/apod")
    suspend fun getAPOD(
        @Query("api_key")
        apikey: String = BuildConfig.API_KEY
    ): APODResponse
}