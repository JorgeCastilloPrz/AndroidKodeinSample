package me.jorgecastillo.kodein.photoslist.domain.interactor

import me.jorgecastillo.kodein.common.domain.interactor.UseCase
import me.jorgecastillo.kodein.common.domain.model.Photo
import me.jorgecastillo.kodein.photoslist.domain.repository.PhotosRepository

class GetPhotos(private val photosRepository: PhotosRepository) :
    UseCase<GetPhotos.Params, List<Photo>>() {

  override fun run(params: Params) = photosRepository.getAll()

  data class Params(val page: Int, val query: String)
}
