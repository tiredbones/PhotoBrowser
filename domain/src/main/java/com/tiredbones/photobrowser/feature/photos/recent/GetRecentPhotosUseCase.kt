package com.tiredbones.photobrowser.feature.photos.recent

import com.tiredbones.photobrowser.entities.PhotoData
import com.tiredbones.photobrowser.feature.photos.PhotosRepository
import io.reactivex.Single
import javax.inject.Inject

class GetRecentPhotosUseCase @Inject constructor(
    private val repository: PhotosRepository
) {

  operator fun invoke(page: Int, perPage: Int): Single<List<PhotoData>> = repository.getRecentPhotos(page, perPage)

}
