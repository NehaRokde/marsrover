package com.example.listellanasaapp.ui.marsrover

import android.provider.ContactsContract.CommonDataKinds.Phone
import com.example.listellanasaapp.data.api.model.Photo

data class MainState(
    val isLoading: Boolean = false,
    val data: List<Photo> = emptyList(),
    val error: String = ""
)
