package me.jorgecastillo.viper.photoslist.domain.repository

import arrow.core.Either
import me.jorgecastillo.viper.common.domain.repository.PhotosNetworkDataSource
import me.jorgecastillo.viper.common.domain.error.Error
import me.jorgecastillo.viper.common.domain.model.Photo

class PhotosRepository(private val networkDataSource: PhotosNetworkDataSource) {
  fun getAll(): Either<Error, List<Photo>> = networkDataSource.getAll()
}
