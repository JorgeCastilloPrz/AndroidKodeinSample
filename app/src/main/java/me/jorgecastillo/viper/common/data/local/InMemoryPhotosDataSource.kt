package me.jorgecastillo.viper.common.data.local

import arrow.core.Either
import me.jorgecastillo.viper.common.domain.error.Error
import me.jorgecastillo.viper.common.domain.model.Photo
import me.jorgecastillo.viper.common.domain.repository.PhotosLocalDataSource

class InMemoryPhotosDataSource : PhotosLocalDataSource {

  override fun getAll(): Either<Error, List<Photo>> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getPhoto(photoId: String): Either<Error, Photo> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun save(photos: List<Photo>) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun save(photo: Photo) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

}
