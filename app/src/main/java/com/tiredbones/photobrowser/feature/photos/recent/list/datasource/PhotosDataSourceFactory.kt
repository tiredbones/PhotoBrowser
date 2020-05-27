package com.tiredbones.photobrowser.feature.photos.recent.list.datasource

import androidx.paging.DataSource
import com.tiredbones.photobrowser.feature.photos.recent.GetRecentPhotosUseCase
import com.tiredbones.photobrowser.feature.photos.recent.PhotosMapper
import com.tiredbones.photobrowser.feature.photos.recent.list.PhotoItem
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PhotosDataSourceFactory @Inject constructor(
    private val getRecentPhotosUseCase: GetRecentPhotosUseCase,
    private val photosMapper: PhotosMapper,
    private val disposables: CompositeDisposable,
    private val onInitialDataLoadCompleted: () -> Unit,
    private val onError: (Throwable) -> Unit
) : DataSource.Factory<Int, PhotoItem>() {

  lateinit var dataSource: PhotosDataSource
    private set

  override fun create(): DataSource<Int, PhotoItem> =
      PhotosDataSource(getRecentPhotosUseCase, photosMapper, disposables, onInitialDataLoadCompleted, onError).also {
        dataSource = it
      }

}
