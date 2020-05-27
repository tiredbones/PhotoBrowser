package com.tiredbones.photobrowser.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.tiredbones.photobrowser.PhotoApplication
import com.tiredbones.photobrowser.R
import com.tiredbones.photobrowser.extensions.subscribe
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

  private val loadingDialog: AlertDialog by lazy {
    AlertDialog.Builder(this, R.style.DialogTransparent)
        .setView(R.layout.dialog_progress)
        .setCancelable(false)
        .create()
  }

  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

  @CallSuper
  override fun onCreate(savedInstanceState: Bundle?) {
    PhotoApplication.applicationComponent.inject(this)
    super.onCreate(savedInstanceState)
  }

  fun observeViewModel(viewModel: BaseViewModel) {
    viewModel.loading.subscribe(this, ::setLoadingVisibility)
  }

  private fun setLoadingVisibility(isVisible: Boolean) {
    if (isVisible) {
      loadingDialog.show()
    } else {
      loadingDialog.dismiss()
    }
  }
}
