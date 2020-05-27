package com.tiredbones.photobrowser.widget

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.tiredbones.photobrowser.R
import com.tiredbones.photobrowser.databinding.DialogErrorBinding

class ErrorDialog : DialogFragment() {

  override fun onAttach(context: Context) {
    if (host !is ErrorDialogCallback) throw IllegalStateException("Host must implement ErrorDialogCallback interface")
    super.onAttach(context)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val binding: DialogErrorBinding = DataBindingUtil.inflate(
        inflater,
        R.layout.dialog_error,
        container,
        false
    )
    binding.buttonTryAgain.setOnClickListener {
      (host as ErrorDialogCallback).onRetryClicked()
      dismiss()
    }
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    isCancelable = false
  }

  interface ErrorDialogCallback {
    fun onRetryClicked()
  }
}