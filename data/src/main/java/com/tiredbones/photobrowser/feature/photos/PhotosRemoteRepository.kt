package com.tiredbones.photobrowser.feature.photos

import com.tiredbones.photobrowser.entities.PhotoData
import com.tiredbones.photobrowser.mapper.map
import com.tiredbones.photobrowser.network.response.RecentPhotosResponse
import io.reactivex.Single
import javax.inject.Inject

class PhotosRemoteRepository @Inject constructor(
    private val photosApi: PhotosApi
) : PhotosRepository {

  override fun getRecentPhotos(page: Int, perPage: Int): Single<List<PhotoData>> =
      photosApi.getRecentPhotos(page, perPage).map(RecentPhotosResponse::map)

}
