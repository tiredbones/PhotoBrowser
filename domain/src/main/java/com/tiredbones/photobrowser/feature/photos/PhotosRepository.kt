package com.tiredbones.photobrowser.feature.photos

import com.tiredbones.photobrowser.entities.PhotoData
import io.reactivex.Single

interface PhotosRepository {

  fun getRecentPhotos(page: Int, perPage: Int): Single<List<PhotoData>>

}
