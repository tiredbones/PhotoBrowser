package com.tiredbones.photobrowser.feature.photos

import com.tiredbones.photobrowser.network.response.RecentPhotosResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosApi {

  @GET("services/rest")
  fun getRecentPhotos(
      @Query("page") page: Int,
      @Query("per_page") per_page: Int,
      @Query("method") method: String = RECENT_PHOTOS_METHOD
  ): Single<RecentPhotosResponse>

  private companion object {
    const val RECENT_PHOTOS_METHOD = "flickr.photos.getRecent"
  }
}
