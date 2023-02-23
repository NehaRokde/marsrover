package com.example.listellanasaapp.data.api.model


import com.google.gson.annotations.SerializedName

data class MarsRoverPhotosResponse(
    @SerializedName("photos")
    val photos: List<Photo>
)