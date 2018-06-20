package me.jorgecastillo.kodein.common.data.local

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import me.jorgecastillo.kodein.common.data.network.PhotosNotFound
import me.jorgecastillo.kodein.common.domain.error.Error
import me.jorgecastillo.kodein.common.domain.model.Photo
import me.jorgecastillo.kodein.common.domain.repository.PhotosLocalDataSource

/**
 * This is an in-memory implementation of the photos data source, mostly for simplicity of the example. It could
 * potentially be a persistence implementation. It gets a TTL constant injected that determines the Time to Live for
 * photos, so photos stored for a longer than the TTL become automatically invalid and removed.
 *
 * For sake of simplicity the list of photos becomes invalid when any photo inside of it is invalid.
 */
class InMemoryPhotosDataSource(private val ttlMillis: Long) : PhotosLocalDataSource {

  private var photos: List<PersistedPhoto> = emptyList()

  override fun getAll(): Either<Error, List<Photo>> = when {
    photos.isEmpty() -> PhotosNotFound().left()
    photos.any { it.isInvalid(ttlMillis) } -> PhotosNotFound().left().also { photos = emptyList(); }
    else -> photos.map { it.photo }.right()
  }

  override fun getPhoto(photoId: String): Either<Error, Photo> {
    val persistedPhoto = photos.find { it.photo.id == photoId }
    return when {
      persistedPhoto == null -> PhotosNotFound().left()
      persistedPhoto.isInvalid(ttlMillis) -> PhotosNotFound().left().also { photos -= persistedPhoto }
      else -> persistedPhoto.photo.right()
    }
  }

  override fun save(photos: List<Photo>) {
    val persistedPhotosToSave = photos.map { PersistedPhoto(System.currentTimeMillis(), it) }

    val photosNotPersistedYet = persistedPhotosToSave - this.photos
    val photosAlreadyPersisted = (persistedPhotosToSave intersect this.photos).map {
      it.copy(storedTimeMillis = System.currentTimeMillis())
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
  val storedTimeMillis: Long,
  val photo: Photo
) {
  override fun equals(other: Any?): Boolean =
    other is PersistedPhoto && photo == other.photo

  override fun hashCode(): Int {
    var result = storedTimeMillis.hashCode()
    result = 31 * result + photo.hashCode()
    return result
  }
}

fun PersistedPhoto.isInvalid(ttlMillis: Long) = System.currentTimeMillis() - storedTimeMillis >= ttlMillis
