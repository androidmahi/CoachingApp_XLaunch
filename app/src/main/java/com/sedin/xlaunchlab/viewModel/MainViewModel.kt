package com.sedin.xlaunchlab.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sedin.xlaunchlab.state.MainUiState
import com.sedin.xlaunchlab.model.uiModels.User
import com.sedin.xlaunchlab.useCase.GetUserListUseCase
import com.sedin.xlaunchlab.utils.ext.getErrorMessage
import com.sedin.xlaunchlab.utils.ext.launchWithHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  private val getUserListUseCase: GetUserListUseCase,
) : ViewModel() {

  private val _state = MutableStateFlow(MainUiState())
  val state: StateFlow<MainUiState> = _state.asStateFlow()

  init {
    getUserDetails()
  }

  fun getUserDetails() {
    viewModelScope.launchWithHandler(
      onCoroutineException = {
        hideLoader()
        updateToastMessage(it.getErrorMessage())
      }) {

      showLoader()

      val userList = getUserListUseCase()
      updateUserList(userList)



      hideLoader()
    }
  }

  fun showLoader() {
    _state.update { it.copy(isLoading = true) }
  }

  fun hideLoader() {
    _state.update { it.copy(isLoading = false) }
  }

  fun updateToastMessage(message: String) {
    _state.update { it.copy(toastMessage = message) }
  }

  fun updateUserList(users: List<User>) {
    _state.update { it.copy(userList = users) }
  }

  fun updateCurrentSelectedRole(selectedRole: String) {
    val updatedRole = if (selectedRole == _state.value.currentSelectedRole) {
      ""
    } else {
      selectedRole
    }
    _state.update { it.copy(currentSelectedRole = updatedRole) }
  }
}