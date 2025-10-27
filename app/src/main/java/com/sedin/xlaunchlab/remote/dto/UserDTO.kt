package com.sedin.xlaunchlab.remote.dto

import com.sedin.xlaunchlab.model.uiModels.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
  val id: String = "",
  val name: String = "",
  val role: String = "",
  val email: String = "",
  @SerialName("username") val userName: String = "",
  val avatar: String = "",
  val coachId: Int = -1
)

fun UserDTO.toUiModel(): User {
  return User(
    id = id,
    name = name,
    role = role,
    email = email,
    userName = userName,
    avatar = avatar,
    coachId = coachId
  )
}

fun Iterable<UserDTO>.toUiModels() = this.map { it.toUiModel() }