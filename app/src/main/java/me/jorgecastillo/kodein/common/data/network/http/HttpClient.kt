package me.jorgecastillo.kodein.common.data.network.http

import me.jorgecastillo.kodein.sample.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient

fun httpClient(headersInterceptor: Interceptor, loggingInterceptor: Interceptor): OkHttpClient =
    OkHttpClient.Builder().addNetworkInterceptor(headersInterceptor).also {
      if (BuildConfig.DEBUG) {
        it.addInterceptor(loggingInterceptor)
      }
    }.build()
