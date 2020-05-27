package com.tiredbones.photobrowser.feature.photos.recent.list.datasource

import androidx.paging.PageKeyedDataSource
import com.tiredbones.photobrowser.extensions.addTo
import com.tiredbones.photobrowser.feature.photos.recent.GetRecentPhotosUseCase
import com.tiredbones.photobrowser.feature.photos.recent.PhotosMapper
import com.tiredbones.photobrowser.feature.photos.recent.list.PhotoItem
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PhotosDataSource(
    private val getRecentPhotosUseCase: GetRecentPhotosUseCase,
    private val photosMapper: PhotosMapper,
    private val disposables: CompositeDisposable,
    private val onInitialDataLoadCompleted: () -> Unit,
    private val onError: (Throwable) -> Unit
) : PageKeyedDataSource<Int, PhotoItem>() {

  override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, PhotoItem>) {
    loadItems(INITIAL_PAGE) {
      onInitialDataLoadCompleted()
      callback.onResult(it, null, INITIAL_PAGE + 1)
    }
  }

  override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PhotoItem>) {
    loadItems(params.key) {
      callback.onResult(it, params.key + 1)
    }
  }

  private fun loadItems(page: Int, onResult: (List<PhotoItem>) -> Unit) {
    getRecentPhotosUseCase(page, PAGE_SIZE)
        .subscribeOn(Schedulers.io())
        .map(photosMapper::mapToUiItems)
        .subscribe({
          onResult(it)
        }, {
          onError(it)
        })
        .addTo(disposables)
  }

  override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PhotoItem>) {
    // no-op
  }

  companion object {
    const val PAGE_SIZE = 100
    private const val INITIAL_PAGE = 1
  }
}
