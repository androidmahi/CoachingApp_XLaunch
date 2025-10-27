package com.sedin.xlaunchlab.repository

import com.sedin.xlaunchlab.model.uiModel.User
import com.sedin.xlaunchlab.remote.XLaunchApiService
import com.sedin.xlaunchlab.remote.dto.toUiModels

class RetrofitDataSource(
  private val apiService: XLaunchApiService
): RemoteRepository {

  override suspend fun getUsers(): List<User> {
    val users = apiService.getUsers().toUiModels()
    return users
  }
}