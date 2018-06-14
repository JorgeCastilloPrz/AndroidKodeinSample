package me.jorgecastillo.viper.common.data.network

import arrow.core.Either
import me.jorgecastillo.viper.common.domain.error.Error
import me.jorgecastillo.viper.photoslist.domain.model.Photo

interface PhotosNetworkDataSource {

  fun getAll(): Either<Error, List<Photo>>
}
