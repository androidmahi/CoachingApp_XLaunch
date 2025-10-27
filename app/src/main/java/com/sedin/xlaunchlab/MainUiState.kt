package com.sedin.xlaunchlab

import com.sedin.xlaunchlab.model.uiModels.User

data class MainUiState(
  val isLoading: Boolean = false,
  val toastMessage: String = "",
  val userList: List<User> =  emptyList(),
  val currentSelectedRole: String = ""
)