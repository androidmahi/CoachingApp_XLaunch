package com.sedin.xlaunchlab.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class XLaunchApplication : Application() {

  override fun onCreate() {
    super.onCreate()

  }

}