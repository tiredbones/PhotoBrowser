package com.tiredbones.photobrowser.di

import com.tiredbones.photobrowser.PhotoApplication
import com.tiredbones.photobrowser.base.BaseActivity
import com.tiredbones.photobrowser.di.module.NetworkModule
import com.tiredbones.photobrowser.di.module.RepositoryModule
import com.tiredbones.photobrowser.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
      ViewModelModule::class,
      RepositoryModule::class,
      NetworkModule::class
    ]
)
interface ApplicationComponent {

  fun inject(app: BaseActivity)

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: PhotoApplication): Builder

    fun build(): ApplicationComponent
  }
}
