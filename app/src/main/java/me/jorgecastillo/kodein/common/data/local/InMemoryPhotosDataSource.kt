package me.jorgecastillo.kodein.common.data.local

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import me.jorgecastillo.kodein.common.data.network.PhotosNotFound
import me.jorgecastillo.kodein.common.domain.error.Error
import me.jorgecastillo.kodein.common.domain.model.Photo
import me.jorgecastillo.kodein.common.domain.repository.PhotosLocalDataSource
import me.jorgecastillo.kodein.detail.domain.interactor.PhotoNotFound

/**
 * This is an in-memory implementation of the photos data source, mostly for simplicity of the
 * example. It could potentially be a persistence implementation.
 */
class InMemoryPhotosDataSource : PhotosLocalDataSource {

  private var photos: List<Photo> = emptyList()

  override fun getAll(): Either<Error, List<Photo>> = if (photos.isEmpty()) PhotosNotFound().left() else photos.right()

  override fun getPhoto(photoId: String): Either<Error, Photo> {
    val photo = photos.find { it.id == photoId }
    return photo?.right() ?: PhotoNotFound().left()
  }

  override fun save(photos: List<Photo>) {
    val photosToAdd = photos.filterNot { this.photos.contains(it) }
    this.photos = this.photos + photosToAdd
  }

  override fun save(photo: Photo) {
    this.photos = this.photos + if (photos.contains(photo)) listOf() else listOf(photo)
  }
}
