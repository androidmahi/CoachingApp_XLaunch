package com.sedin.xlaunchlab.model.uiModel

data class User(
  val id: String = "",
  val name: String = "",
  val role: String = "",
  val email: String = "",
  val userName: String = "",
  val avatar: String = "",
  val coachId: Int = -1
)