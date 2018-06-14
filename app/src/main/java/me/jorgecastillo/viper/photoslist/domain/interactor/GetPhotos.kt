package me.jorgecastillo.viper.photoslist.domain.interactor

import me.jorgecastillo.viper.common.domain.interactor.Interactor
import me.jorgecastillo.viper.photoslist.domain.model.Photo
import me.jorgecastillo.viper.photoslist.domain.repository.PhotosRepository

class GetPhotos(private val photosRepository: PhotosRepository) :
    Interactor<GetPhotos.Params, List<Photo>>() {

  override suspend fun run(params: Params) = photosRepository.getAll()

  data class Params(val page: Int, val query: String)
}
