package com.sedin.xlaunchlab.repository

import com.sedin.xlaunchlab.model.uiModel.User

interface RemoteRepository {

  suspend fun getUsers(): List<User>
}