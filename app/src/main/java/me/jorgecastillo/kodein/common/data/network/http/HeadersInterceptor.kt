package me.jorgecastillo.kodein.common.data.network.http

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

fun loggingInterceptor(): HttpLoggingInterceptor =
  HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

class HeadersInterceptor : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val request = chain.request()
      .newBuilder()
      .addHeader("Accept-Version", "v1")
      .addHeader("Authorization", "Client-ID e0bf5ecd991a987cde8231a5bfecc35bbb4ddf282d3a5a44091cf606b916dbb8")
      .build()
    return chain.proceed(request)
  }
}
