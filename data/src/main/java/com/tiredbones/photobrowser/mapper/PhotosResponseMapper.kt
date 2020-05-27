package com.tiredbones.photobrowser.mapper

import com.tiredbones.photobrowser.entities.PhotoData
import com.tiredbones.photobrowser.network.response.RecentPhotosResponse

fun RecentPhotosResponse.map() = photos.photo.map {
  PhotoData(
      id = it.id,
      owner = it.owner,
      secret = it.secret,
      server = it.server,
      farm = it.farm,
      title = it.title
  )
}
