package com.sedin.xlaunchlab.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sedin.xlaunchlab.remote.XLaunchApiService
import com.sedin.xlaunchlab.repository.RemoteRepository
import com.sedin.xlaunchlab.repository.RetrofitDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

  private const val BASE_URL = "https://launch-labs-api-test.fusecup.workers.dev/"

  @Provides
  @Singleton
  fun provideJson(): Json = Json {
    ignoreUnknownKeys = true
    isLenient = true
    prettyPrint = false
    encodeDefaults = true
  }

  @Provides
  @Singleton
  fun provideRetrofit(json: Json): Retrofit {

    val logger = HttpLoggingInterceptor()
      .apply {
        level = HttpLoggingInterceptor.Level.BODY
      }

    val okHttpClient = OkHttpClient.Builder()
      .addInterceptor(logger)
      .connectTimeout(30, TimeUnit.SECONDS)
      .readTimeout(30, TimeUnit.SECONDS)
      .writeTimeout(30, TimeUnit.SECONDS)
      .build()

    val contentType = "application/json".toMediaType()

    return Retrofit.Builder()
      .baseUrl(BASE_URL)
      .client(okHttpClient)
      .addConverterFactory(json.asConverterFactory(contentType))
      .build()
  }

  @Provides
  @Singleton
  fun provideApiService(retrofit: Retrofit): XLaunchApiService {
    return retrofit.create(XLaunchApiService::class.java)
  }

  @Provides
  @Singleton
  fun provideRemoteRepository(apiService: XLaunchApiService): RemoteRepository {
    return RetrofitDataSource(apiService)
  }

}