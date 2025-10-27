package com.sedin.xlaunchlab.remote

import com.sedin.xlaunchlab.remote.dto.UserDTO
import retrofit2.http.GET

interface XLaunchApiService {

  @GET("users")
  suspend fun getUsers(): List<UserDTO>

}