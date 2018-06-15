package me.jorgecastillo.viper.common.data.network

import arrow.core.Either
import arrow.core.Try
import arrow.core.left
import arrow.core.right
import me.jorgecastillo.viper.common.data.network.mapper.toDomain
import me.jorgecastillo.viper.common.domain.error.Error
import me.jorgecastillo.viper.common.domain.model.Photo
import me.jorgecastillo.viper.common.domain.repository.PhotosNetworkDataSource
import me.jorgecastillo.viper.detail.domain.interactor.PhotoNotFound

class PhotosNotFound : Error.FeatureError()

class UnsplashPhotosDataSource(private val service: UnsplashService) : PhotosNetworkDataSource {

  override fun getAll(): Either<Error, List<Photo>> =
      Try {
        service.getPhotos().execute()
      }.fold(ifFailure = {
        Error.ServerError().left()
      }, ifSuccess = { response ->
        if (response.isSuccessful) {
          val body = response.body()!!
          body.toDomain().right()
        } else PhotosNotFound().left()
      })

  override fun getPhoto(photoId: String): Either<Error, Photo> =
      Try {
        service.getPhoto(photoId).execute()
      }.fold(ifFailure = {
        Error.ServerError().left()
      }, ifSuccess = { response ->
        if (response.isSuccessful) {
          val body = response.body()!!
          body.toDomain().right()
        } else PhotoNotFound().left()
      })
}
