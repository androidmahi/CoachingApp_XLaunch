package com.sedin.xlaunchlab.utils.ext

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

fun CoroutineScope.launchWithHandler(
  dispatcher: CoroutineDispatcher = Dispatchers.IO,
  onCoroutineException: (Throwable) -> Unit,
  block: suspend CoroutineScope.() -> Unit
): Job {
  val ceh = CoroutineExceptionHandler { _, throwable ->
    if (throwable !is CancellationException) {
      onCoroutineException(throwable)
    }
  }
  return launch(dispatcher + ceh) {
    block()
  }
}

fun Throwable.getErrorMessage(): String {
  return when (this) {
    is java.net.UnknownHostException -> "No internet connection. Please check your network."
    is java.net.SocketTimeoutException -> "Connection timed out. Please try again."
    is retrofit2.HttpException -> {
      val message = this.message
      return if (message.isNullOrEmpty().not()) {
        this.message.orEmpty()
      } else {
        when (this.code()) {
          400 -> "Invalid request. Please check your input."
          401 -> "Unauthorized. Please check your credentials."
          403 -> "Access denied."
          404 -> "Requested resource not found."
          500 -> "Server error. Please try again later."
          else -> "Unexpected error: ${this.code()}."
        }
      }
    }
    is kotlinx.coroutines.CancellationException -> "Request was cancelled."
    else -> this.message ?: "Something went wrong. Please try again."
  }
}

fun Context.showToast(message: String) {
  Toast.makeText(this, message, android.widget.Toast.LENGTH_SHORT).show()
}