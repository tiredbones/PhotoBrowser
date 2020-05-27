package com.tiredbones.photobrowser.di.module

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.tiredbones.photobrowser.BuildConfig
import com.tiredbones.photobrowser.feature.photos.PhotosApi
import com.tiredbones.photobrowser.network.interceptor.ApiParametersInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

  @Provides
  @Singleton
  internal fun provideHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()
    builder.addInterceptor(ApiParametersInterceptor(BuildConfig.API_KEY))

    if (BuildConfig.DEBUG) {
      builder.addInterceptor(HttpLoggingInterceptor())
          .addNetworkInterceptor(StethoInterceptor())
    }
    return builder.build()
  }

  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
      Retrofit.Builder()
          .baseUrl(BuildConfig.BASE_URL)
          .client(okHttpClient)
          .addConverterFactory(GsonConverterFactory.create())
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .build()

  @Provides
  internal fun providePhotosApi(retrofit: Retrofit): PhotosApi = retrofit.create(PhotosApi::class.java)
}
