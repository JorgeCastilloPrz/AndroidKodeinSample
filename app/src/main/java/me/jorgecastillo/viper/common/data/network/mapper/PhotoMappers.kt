package me.jorgecastillo.viper.common.data.network.mapper

import me.jorgecastillo.viper.common.data.network.model.PhotoDto
import me.jorgecastillo.viper.photoslist.domain.model.Photo

fun List<PhotoDto>.toDomain(): List<Photo> = this.map { it.toDomain() }

fun PhotoDto.toDomain(): Photo = Photo(this.urls.regular)
