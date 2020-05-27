package com.tiredbones.photobrowser.feature.photos.recent

import com.tiredbones.photobrowser.BuildConfig
import com.tiredbones.photobrowser.entities.PhotoData
import com.tiredbones.photobrowser.feature.photos.recent.list.PhotoItem
import javax.inject.Inject

class PhotosMapper @Inject constructor() {

  fun mapToUiItems(photoData: List<PhotoData>) =
      photoData.map {
        PhotoItem(
            id = it.id,
            imageUrl = getImageUrl(it.farm, it.server, it.id, it.secret),
            title = it.title
        )
      }

  private fun getImageUrl(farmId: Int, server: String, id: String, secret: String) =
      BuildConfig.FARM_URL.format(farmId, server, id, secret)
}
