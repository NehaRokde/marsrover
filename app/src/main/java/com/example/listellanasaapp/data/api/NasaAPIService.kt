package com.example.listellanasaapp.data.api

import com.example.listellanasaapp.BuildConfig
import com.example.listellanasaapp.data.model.APODResponse
import com.example.listellanasaapp.data.model.MarsRoverPhotosResponse
import com.example.listellanasaapp.data.model.RecentPhotosResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NasaAPIService {

    // To get APOD
    @GET("/planetary/apod")
    suspend fun getAPOD(
        @Query("api_key")
        apikey: String = BuildConfig.API_KEY
    ): APODResponse

    // To get Recent Photos
    @GET("/planetary/apod")
    suspend fun getRecentPhotos(
        @Query("count")
        count: Int,
        @Query("api_key")
        apikey: String = BuildConfig.API_KEY,
    ): RecentPhotosResponse

    // To get Mars Rover Photos
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