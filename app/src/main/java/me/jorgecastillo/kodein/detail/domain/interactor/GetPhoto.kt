package me.jorgecastillo.kodein.detail.domain.interactor

import me.jorgecastillo.kodein.common.domain.error.Error
import me.jorgecastillo.kodein.common.domain.interactor.Interactor
import me.jorgecastillo.kodein.common.domain.model.Photo
import me.jorgecastillo.kodein.photoslist.domain.repository.PhotosRepository

class PhotoNotFound : Error.FeatureError()

class GetPhoto(private val photosRepository: PhotosRepository) : Interactor<String, Photo>() {

  override suspend fun run(params: String) = photosRepository.getPhoto(params)
}
