package me.jorgecastillo.viper.common.data.network.mapper

import me.jorgecastillo.viper.common.data.network.model.PhotoDto
import me.jorgecastillo.viper.photoslist.domain.model.Photo
import me.jorgecastillo.viper.photoslist.domain.model.PhotosPage

fun PhotosPage.toDomain(): List<Photo> = this.photos.map { it.toDomain() }

fun PhotoDto.toDomain(): Photo = Photo(this.urls.regular)
