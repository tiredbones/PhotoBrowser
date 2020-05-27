package com.tiredbones.photobrowser.di.module

import androidx.lifecycle.ViewModelProvider
import com.tiredbones.photobrowser.base.BaseViewModel
import com.tiredbones.photobrowser.di.ViewModelFactory
import com.tiredbones.photobrowser.di.ViewModelKey
import com.tiredbones.photobrowser.feature.photos.recent.RecentPhotosViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelModule {

  @Binds
  @Singleton
  internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

  @Binds
  @IntoMap
  @ViewModelKey(RecentPhotosViewModel::class)
  internal abstract fun bindRecentPhotosViewModel(viewModel: RecentPhotosViewModel): BaseViewModel

}
