package com.sedin.xlaunchlab.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sedin.xlaunchlab.composable.MainScreen
import com.sedin.xlaunchlab.utils.theme.XLaunchLabTheme
import com.sedin.xlaunchlab.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  val viewModel: MainViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {

      val uiState = viewModel.state.collectAsStateWithLifecycle().value

      XLaunchLabTheme {
        MainScreen(
          uiState = uiState,
          onFetchInitialData = {
            viewModel.getUserDetails()
          },
          onResetToastMessage = {
            viewModel.updateToastMessage("")
          },
          onLoadMoreClick = {
            //todo load next page data
          },
          onRoleSelected = { selectedRole ->
            viewModel.updateCurrentSelectedRole(selectedRole)
          }
        )
      }
    }
  }
}