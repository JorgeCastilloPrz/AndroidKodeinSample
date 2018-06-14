package me.jorgecastillo.viper.common.data.network.service

import okhttp3.logging.HttpLoggingInterceptor

fun loggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
