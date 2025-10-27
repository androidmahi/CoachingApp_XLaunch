package com.sedin.xlaunchlab.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sedin.xlaunchlab.utils.commonComposable.HorizontalSpacer

@Composable
fun UserRolesChips(
  modifier: Modifier = Modifier,
  roles: List<String>,
  selectedRole: String,
  onRoleClick: (String) -> Unit,
) {
  LazyRow(
    modifier = modifier
  ) {
    itemsIndexed(items = roles) { index, role ->
      UserRoleChip(
        role = role,
        isSelected = selectedRole == role,
        onClick = {
          onRoleClick(role)
        }
      )
      HorizontalSpacer(3)
    }
  }
}

@Composable
fun UserRoleChip(
  modifier: Modifier = Modifier,
  role: String,
  isSelected: Boolean,
  onClick: () -> Unit
) {
  Text(
    modifier = modifier
      .clickable {
        onClick()
      }
      .border(
        width = 1.dp,
        color = if (isSelected) Color.Blue else Color.Black,
        shape = RoundedCornerShape(16.dp)
      )
      .background(if (isSelected) Color.Blue else Color.Transparent, shape = RoundedCornerShape(16.dp))
      .padding(10.dp)
    ,
    text = role,
    color = if (isSelected) Color.White else Color.Black
  )
}