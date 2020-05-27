package com.tiredbones.photobrowser.network.response

import com.google.gson.annotations.SerializedName

data class RecentPhotosResponse(
    @SerializedName("photos") val photos: PhotosPagerResponse,
    @SerializedName("stat") val stat: String?
)
