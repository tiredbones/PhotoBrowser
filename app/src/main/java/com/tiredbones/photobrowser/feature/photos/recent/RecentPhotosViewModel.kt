package com.tiredbones.photobrowser.feature.photos.recent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.tiredbones.logger.Logger
import com.tiredbones.photobrowser.base.BaseViewModel
import com.tiredbones.photobrowser.extensions.TAG
import com.tiredbones.photobrowser.feature.photos.recent.list.PhotoItem
import com.tiredbones.photobrowser.feature.photos.recent.list.datasource.PhotosDataSource.Companion.PAGE_SIZE
import com.tiredbones.photobrowser.feature.photos.recent.list.datasource.PhotosDataSourceFactory
import javax.inject.Inject

class RecentPhotosViewModel @Inject constructor(
    getRecentPhotosUseCase: GetRecentPhotosUseCase,
    photosMapper: PhotosMapper
) : BaseViewModel() {

  val photos: LiveData<PagedList<PhotoItem>>
  val showError = MutableLiveData<Unit>()

  private val sourceFactory: PhotosDataSourceFactory

  init {
    loading.postValue(true)

    sourceFactory = PhotosDataSourceFactory(getRecentPhotosUseCase, photosMapper, disposables,
        onInitialDataLoadCompleted = {
          loading.postValue(false)
        },
        onError = {
          showError.postValue(Unit)
          loading.postValue(false)
          Logger.e(TAG, it)
        }
    )
    val config = PagedList.Config.Builder()
        .setPageSize(PAGE_SIZE)
        .setInitialLoadSizeHint(PAGE_SIZE * 2)
        .setEnablePlaceholders(false)
        .build()

    photos = LivePagedListBuilder(sourceFactory, config).build()
  }

  fun onRetryClicked() {
    showError.value = null
    loading.value = true
    sourceFactory.dataSource.invalidate()
  }
}
