package me.jorgecastillo.viper.common.data.network.http

import okhttp3.logging.HttpLoggingInterceptor

fun loggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
