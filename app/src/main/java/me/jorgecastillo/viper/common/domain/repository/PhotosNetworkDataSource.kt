package me.jorgecastillo.viper.common.domain.repository

import arrow.core.Either
import me.jorgecastillo.viper.common.domain.error.Error
import me.jorgecastillo.viper.common.domain.model.Photo

/**
 * The data source contracts are part of the domain layer. Their implementations belong to the data
 * layer.
 */
interface PhotosNetworkDataSource {

  fun getAll(): Either<Error, List<Photo>>

  fun getPhoto(photoId: String): Either<Error, Photo>
}
