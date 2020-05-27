package util

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

class LiveDataTestObserver<T> : Observer<T> {

  val observedValues = mutableListOf<T>()

  override fun onChanged(value: T?) {
    value?.let { observedValues.add(it) }
  }
}

fun <T> LiveData<T>.testObserver() = LiveDataTestObserver<T>()
    .also { observeForever(it) }
