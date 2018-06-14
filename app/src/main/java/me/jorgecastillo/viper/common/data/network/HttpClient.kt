package me.jorgecastillo.viper.common.data.network

import me.jorgecastillo.viper.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient

fun httpClient(headersInterceptor: Interceptor, loggingInterceptor: Interceptor): OkHttpClient =
    OkHttpClient.Builder().addNetworkInterceptor(headersInterceptor).also {
      if (BuildConfig.DEBUG) {
        it.addInterceptor(loggingInterceptor)
      }
    }.build()
