package com.sedin.xlaunchlab.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sedin.xlaunchlab.compose.MainScreen
import com.sedin.xlaunchlab.utils.theme.XLaunchLabTheme
import com.sedin.xlaunchlab.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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