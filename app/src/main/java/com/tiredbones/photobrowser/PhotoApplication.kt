package com.tiredbones.photobrowser

import android.app.Application
import android.os.Looper
import com.facebook.stetho.Stetho
import com.tiredbones.logger.Logger
import com.tiredbones.photobrowser.di.ApplicationComponent
import com.tiredbones.photobrowser.di.DaggerApplicationComponent
import com.tiredbones.photobrowser.log.LogcatLogger
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers

class PhotoApplication : Application() {

  companion object {
    @JvmStatic
    lateinit var applicationComponent: ApplicationComponent
      private set
  }

  override fun onCreate() {
    super.onCreate()
    init()
  }

  private fun init() {
    initDaggerComponent()
    initStetho()
    configureRx()
    initLogger()
  }

  private fun initDaggerComponent() {
    applicationComponent = DaggerApplicationComponent.builder().application(this).build()
  }

  private fun initStetho() {
    Stetho.initializeWithDefaults(this)
  }

  private fun configureRx() {
    RxAndroidPlugins.setMainThreadSchedulerHandler { AndroidSchedulers.from(Looper.getMainLooper(), true) }
  }

  @Suppress("ConstantConditionIf")
  private fun initLogger() {
    if (BuildConfig.DEBUG) {
      Logger.addLogger(LogcatLogger())
    }
  }
}
