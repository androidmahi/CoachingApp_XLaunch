package com.sedin.xlaunchlab.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.sedin.xlaunchlab.model.uiModel.User
import com.sedin.xlaunchlab.utils.commonComposable.HorizontalSpacer
import com.sedin.xlaunchlab.utils.commonComposable.VerticalSpacer

@Composable
fun UserDetailCard(
  modifier: Modifier = Modifier,
  detail: User
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(5.dp),
    horizontalAlignment = Alignment.Start
  ) {

    Column(
      modifier = Modifier
        .fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {

      Box(
        modifier = Modifier
          .size(100.dp)
          .clip(RoundedCornerShape(16.dp))
      ) {
        AsyncImage(
          modifier = Modifier
            .fillMaxSize(),
          model = detail.avatar,
          contentDescription = "User Avatar",
        )
      }

      VerticalSpacer(10)
    }

    DetailRow(
      label = "Id",
      value = detail.id
    )
    VerticalSpacer(3)

    DetailRow(
      label = "Name",
      value = detail.name
    )
    VerticalSpacer(3)

    DetailRow(
      label = "Role",
      value = detail.role
    )
    VerticalSpacer(3)

    DetailRow(
      label = "Email",
      value = detail.email
    )
    VerticalSpacer(3)

    DetailRow(
      label = "UserName",
      value = detail.userName
    )
    VerticalSpacer(3)

    DetailRow(
      label = "CoachId",
      value = detail.userName
    )
    VerticalSpacer(3)
  }
}


@Composable
fun DetailRow(
  modifier: Modifier = Modifier,
  label: String,
  value: String
) {
  Row(
    modifier = modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Text(text = label)

    HorizontalSpacer(5)

    Text(text = value)
  }
}