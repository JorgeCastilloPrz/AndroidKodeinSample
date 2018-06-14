package me.jorgecastillo.viper.common.data.network.service

import okhttp3.Interceptor
import okhttp3.Response

class HeadersInterceptor : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val request = chain.request().newBuilder()
        .addHeader("Accept-Version", "v1")
        .addHeader("Authorization", "Client-ID e0bf5ecd991a987cde8231a5bfecc35bbb4ddf282d3a5a44091cf606b916dbb8")
        .build()
    return chain.proceed(request)
  }
}
