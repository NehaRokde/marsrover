package com.example.listellanasaapp.domain.repository

import com.example.listellanasaapp.data.api.NasaAPIService
import com.example.listellanasaapp.data.model.APODResponse
import com.example.listellanasaapp.data.model.MarsRoverPhotosResponse
import com.example.listellanasaapp.data.model.RecentPhotosResponse
import com.example.listellanasaapp.data.util.Resource
import javax.inject.Inject

class NasaRepository @Inject constructor(
    private val apiService: NasaAPIService
) {

    suspend fun getAPOD(): APODResponse {
        return apiService.getAPOD()
    }

    suspend fun getRecentPhotos(
        count: Int
    ): Resource<RecentPhotosResponse> {
        return try {
            val result = apiService.getRecentPhotos(count)
            Resource.Success(data = result)

        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    suspend fun getMarsRoverPhotos(
        roverPath: String,
        camera: String
    ): Resource<MarsRoverPhotosResponse> {
        return try {
            val result = apiService.getRoverPhotoByName(roverPath, camera)
            Resource.Success(data = result)

        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }


}