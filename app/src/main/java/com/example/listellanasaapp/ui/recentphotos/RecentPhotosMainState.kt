package com.example.listellanasaapp.ui.recentphotos

import android.provider.ContactsContract.CommonDataKinds.Phone
import com.example.listellanasaapp.data.api.model.Photo
import com.example.listellanasaapp.data.api.model.RecentPhotosResponseItem

data class RecentPhotosMainState(
    val isLoading: Boolean = false,
    val data: List<RecentPhotosResponseItem> = emptyList(),
    val error: String = ""
)
