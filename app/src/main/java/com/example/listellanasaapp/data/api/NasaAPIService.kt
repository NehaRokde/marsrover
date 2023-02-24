package com.example.listellanasaapp.data.api

import com.example.listellanasaapp.BuildConfig
import com.example.listellanasaapp.data.api.model.APODResponse
import com.example.listellanasaapp.data.api.model.MarsRoverPhotosResponse
import com.example.listellanasaapp.data.api.model.RecentPhotosResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NasaAPIService {

    @GET("/planetary/apod")
    suspend fun getAPOD(
        @Query("api_key")
        apikey: String = BuildConfig.API_KEY
    ): APODResponse

    @GET("/planetary/apod")
    suspend fun getRecentPhotos(
        @Query("count")
        count: Int,
        @Query("api_key")
        apikey: String = BuildConfig.API_KEY,
    ): RecentPhotosResponse
    @GET("mars-photos/api/v1/rovers/{rover}/photos")
    suspend fun getRoverPhotoByName(
        @Path("rover") rover: String,
        @Query("camera")
        camera: String,
        @Query("sol")
        sol: Int = 1000,
        @Query("api_key")
    apikey: String = BuildConfig.API_KEY
    ): MarsRoverPhotosResponse
}