package me.jorgecastillo.viper.common.data.network.service

import okhttp3.Interceptor
import okhttp3.Response

class HeadersInterceptor : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val request = chain.request().newBuilder()
        .addHeader("Accept-Version", "v1")
        .addHeader("Authorization", "Client-ID 6f4d490283b7b82eb24dfab9bf886f59c4331e4be62365505bac6c0d0d995e3d")
        .build()
    return chain.proceed(request)
  }
}
