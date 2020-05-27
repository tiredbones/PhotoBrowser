package com.tiredbones.photobrowser.feature.photos.recent

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.tiredbones.photobrowser.R
import com.tiredbones.photobrowser.base.BaseActivity
import com.tiredbones.photobrowser.databinding.ActivityRecentPhotosBinding
import com.tiredbones.photobrowser.extensions.getViewModel
import com.tiredbones.photobrowser.extensions.subscribe
import com.tiredbones.photobrowser.feature.photos.recent.list.PhotosAdapter
import com.tiredbones.photobrowser.widget.ErrorDialog
import com.tiredbones.photobrowser.widget.pager.DepthPageTransformer

@Suppress("RemoveExplicitTypeArguments")
class RecentPhotosActivity : BaseActivity(), ErrorDialog.ErrorDialogCallback {

  private val viewModel: RecentPhotosViewModel by lazy {
    getViewModel<RecentPhotosViewModel>(viewModelFactory)
  }

  private val binding: ActivityRecentPhotosBinding by lazy {
    DataBindingUtil.setContentView<ActivityRecentPhotosBinding>(this, R.layout.activity_recent_photos)
  }

  @SuppressLint("WrongConstant")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    observeViewModel(viewModel)
    binding.lifecycleOwner = this

    val adapter = PhotosAdapter()
    binding.pager.adapter = adapter
    binding.pager.offscreenPageLimit = PREFETCH_PAGES
    binding.pager.setPageTransformer(DepthPageTransformer())

    viewModel.photos.subscribe(this, adapter::submitList)
    viewModel.showError.subscribe(this) {
      ErrorDialog().showNow(supportFragmentManager, null)
    }
  }

  override fun onRetryClicked() {
    viewModel.onRetryClicked()
  }

  companion object {
    private const val PREFETCH_PAGES = 3
  }
}
