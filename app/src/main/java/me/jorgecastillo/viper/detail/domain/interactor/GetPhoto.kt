package me.jorgecastillo.viper.detail.domain.interactor

import me.jorgecastillo.viper.common.domain.repository.PhotosNetworkDataSource
import me.jorgecastillo.viper.common.domain.error.Error
import me.jorgecastillo.viper.common.domain.interactor.Interactor
import me.jorgecastillo.viper.common.domain.model.Photo

class PhotoNotFound : Error.FeatureError()

class GetPhoto(private val photoDataSource: PhotosNetworkDataSource) : Interactor<String, Photo>() {

  override suspend fun run(params: String) = photoDataSource.getPhoto(params)
}
