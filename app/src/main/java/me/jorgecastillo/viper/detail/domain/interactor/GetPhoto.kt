package me.jorgecastillo.viper.detail.domain.interactor

import me.jorgecastillo.viper.common.domain.error.Error
import me.jorgecastillo.viper.common.domain.interactor.Interactor
import me.jorgecastillo.viper.common.domain.model.Photo
import me.jorgecastillo.viper.photoslist.domain.repository.PhotosRepository

class PhotoNotFound : Error.FeatureError()

class GetPhoto(private val photosRepository: PhotosRepository) : Interactor<String, Photo>() {

  override suspend fun run(params: String) = photosRepository.getPhoto(params)
}
