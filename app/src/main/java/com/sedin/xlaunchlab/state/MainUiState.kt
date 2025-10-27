package com.sedin.xlaunchlab.state

import com.sedin.xlaunchlab.model.uiModel.User

data class MainUiState(
  val isLoading: Boolean = false,
  val toastMessage: String = "",
  val userList: List<User> =  emptyList(),
  val currentSelectedRole: String = ""
)