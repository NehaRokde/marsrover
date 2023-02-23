package com.example.listellanasaapp.repository

import com.example.listellanasaapp.data.api.NasaAPI
import com.example.listellanasaapp.data.api.model.APODResponse
import javax.inject.Inject

class NasaRepo @Inject constructor(
    private val nasaAPI: NasaAPI
) {

    suspend fun getAPOD(): APODResponse {
        return nasaAPI.getAPOD()
    }

}