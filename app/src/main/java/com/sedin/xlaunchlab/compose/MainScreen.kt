package com.sedin.xlaunchlab.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sedin.xlaunchlab.state.MainUiState
import com.sedin.xlaunchlab.utils.commonComposable.VerticalSpacer
import com.sedin.xlaunchlab.utils.ext.showToast

@Composable
fun MainScreen(
  modifier: Modifier = Modifier,
  uiState: MainUiState,
  onFetchInitialData: () -> Unit,
  onResetToastMessage: () -> Unit,
  onLoadMoreClick: () -> Unit,
  onRoleSelected: (String) -> Unit,
) {

  val localContext = LocalContext.current

  LaunchedEffect(Unit) {
    onFetchInitialData()
  }

  LaunchedEffect(uiState.toastMessage) {
    if (uiState.toastMessage.isNotEmpty()) {
      localContext.showToast(uiState.toastMessage)
      onResetToastMessage()
    }
  }

  val filteredUserList = remember(
    key1 = uiState.currentSelectedRole,
    key2 = uiState.userList
  ) {
    if (uiState.currentSelectedRole.isEmpty()) {
      uiState.userList
    } else {
      uiState.userList.filter { it.role == uiState.currentSelectedRole }
    }
  }

  Scaffold(
    modifier = modifier
  ) { paddingValues ->

    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {

      if (uiState.isLoading) {
        CircularProgressIndicator()
      } else if (uiState.userList.isEmpty()) {
        Text(text = "No users found")
      } else {
        LazyColumn(
          modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
          ) {

          item {
            val rolesList = uiState
              .userList
              .map { it.role }
              .distinct()

            VerticalSpacer(3)
            UserRolesChips(
              roles = rolesList,
              selectedRole = uiState.currentSelectedRole,
              onRoleClick = onRoleSelected
            )
            VerticalSpacer(5)
          }

          itemsIndexed(items = filteredUserList, key = { _, user -> user.id }) { index, user ->
            UserDetailCard(detail = user)
            VerticalSpacer(15)

            if (index == uiState.userList.lastIndex) {
              Text(
                modifier = Modifier
                  .fillMaxWidth()
                  .clickable {
                    onLoadMoreClick()
                  },
                text = "Load More...",
                textAlign = TextAlign.Center,
                style = TextStyle(
                  fontWeight = FontWeight.Bold,
                  fontSize = 18.sp
                )
              )
              VerticalSpacer(15)
            }
          }

        }
      }

    }

  }
}
