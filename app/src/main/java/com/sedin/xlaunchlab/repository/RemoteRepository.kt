package com.sedin.xlaunchlab.repository

import com.sedin.xlaunchlab.model.uiModels.User

interface RemoteRepository {

  suspend fun getUsers(): List<User>
}