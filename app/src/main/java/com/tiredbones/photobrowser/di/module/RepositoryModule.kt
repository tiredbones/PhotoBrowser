package com.tiredbones.photobrowser.di.module

import com.tiredbones.photobrowser.feature.photos.PhotosRemoteRepository
import com.tiredbones.photobrowser.feature.photos.PhotosRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

  @Binds
  internal abstract fun bindRepository(repository: PhotosRemoteRepository): PhotosRepository

}
