package me.jorgecastillo.kodein.photoslist.domain.repository

import arrow.core.Either
import arrow.core.right
import me.jorgecastillo.kodein.common.domain.error.Error
import me.jorgecastillo.kodein.common.domain.model.Photo
import me.jorgecastillo.kodein.common.domain.repository.CachePolicy
import me.jorgecastillo.kodein.common.domain.repository.CachePolicy.*
import me.jorgecastillo.kodein.common.domain.repository.PhotosLocalDataSource
import me.jorgecastillo.kodein.common.domain.repository.PhotosNetworkDataSource

/**
 * Repositories are part of domain, coordinate different sources of data, and can also compose data
 * from different sources.
 */
class PhotosRepository(
    private val localDataSource: PhotosLocalDataSource,
    private val networkDataSource: PhotosNetworkDataSource) {

  fun getAll(policy: CachePolicy = CachePolicy.LocalFirst): Either<Error, List<Photo>> {
    return when (policy) {
      NetworkFirst -> networkDataSource.getAll().fold(
          { localDataSource.getAll() },
          { localDataSource.save(it); it.right() })

      LocalFirst -> localDataSource.getAll().fold(
          { networkDataSource.getAll().map { localDataSource.save(it); it } },
          { it.right() })

      LocalOnly -> localDataSource.getAll()

      NetworkOnly -> networkDataSource.getAll().map { localDataSource.save(it); it }
    }
  }

  fun getPhoto(photoId: String, policy: CachePolicy = CachePolicy.LocalFirst): Either<Error, Photo> {
    return when (policy) {
      NetworkFirst -> networkDataSource.getPhoto(photoId).fold(
          { localDataSource.getPhoto(photoId) },
          { localDataSource.save(it); it.right() })

      LocalFirst -> localDataSource.getPhoto(photoId).fold(
          { networkDataSource.getPhoto(photoId).map { localDataSource.save(it); it } },
          { it.right() })

      LocalOnly -> localDataSource.getPhoto(photoId)

      NetworkOnly -> networkDataSource.getPhoto(photoId).map { localDataSource.save(it); it }
    }
  }
}
