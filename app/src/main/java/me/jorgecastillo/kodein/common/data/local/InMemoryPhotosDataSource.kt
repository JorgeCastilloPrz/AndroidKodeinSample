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
 * example. It could potentially be a persistence implementation. It gets a TTL injected so photos stored for a time
 * longer than the TTL are automatically removed and the get operation returns an error.
 */
class InMemoryPhotosDataSource(private val ttlMillis: Long) : PhotosLocalDataSource {

  private var photos: List<PersistedPhoto> = emptyList()

  override fun getAll(): Either<Error, List<Photo>> =
    if (photos.isEmpty() || photos.any { it.isInvalid(ttlMillis) }) {
      photos = emptyList()
      PhotosNotFound().left()
    } else {
      photos.map { it.photo }
        .right()
    }

  override fun getPhoto(photoId: String): Either<Error, Photo> {
    val photo = photos.find { it.photo.id == photoId }
    return when {
      photo == null -> PhotoNotFound().left()
      photo.isInvalid(ttlMillis) -> {
        photos -= photo
        PhotoNotFound().left()
      }
      else -> photo.photo.right()
    }
  }

  override fun save(photos: List<Photo>) {
    val persistedPhotosToSave = photos.map { PersistedPhoto(System.currentTimeMillis(), it) }

    val photosNotPersistedYet = persistedPhotosToSave - this.photos
    val photosAlreadyPersisted = (persistedPhotosToSave intersect this.photos).map {
      it.copy(whenSaved = System.currentTimeMillis())
    }

    this.photos = photosNotPersistedYet + photosAlreadyPersisted
  }

  override fun save(photo: Photo) {
    val persistedPhoto = PersistedPhoto(System.currentTimeMillis(), photo)

    this.photos = if (photos.contains(persistedPhoto)) {
      photos - persistedPhoto + persistedPhoto
    } else
      photos + persistedPhoto
  }
}

data class PersistedPhoto(
  val whenSaved: Long,
  val photo: Photo
) {
  override fun equals(other: Any?): Boolean =
    other is PersistedPhoto && photo == other.photo

  override fun hashCode(): Int {
    var result = whenSaved.hashCode()
    result = 31 * result + photo.hashCode()
    return result
  }
}

fun PersistedPhoto.isInvalid(ttlMillis: Long) = System.currentTimeMillis() - whenSaved >= ttlMillis
