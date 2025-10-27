package com.sedin.xlaunchlab.useCase

import com.sedin.xlaunchlab.model.uiModel.User
import com.sedin.xlaunchlab.repository.RemoteRepository
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
  private val repository: RemoteRepository
) {
  suspend operator fun invoke(): List<User> {
    return repository.getUsers()
  }
}