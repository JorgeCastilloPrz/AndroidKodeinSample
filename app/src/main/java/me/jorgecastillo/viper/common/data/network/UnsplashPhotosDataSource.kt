package me.jorgecastillo.viper.common.data.network

import arrow.core.Either
import arrow.core.Try
import arrow.core.left
import me.jorgecastillo.viper.common.data.network.model.PhotoDto
import me.jorgecastillo.viper.common.data.network.service.UnsplashService
import me.jorgecastillo.viper.common.domain.error.Error
import me.jorgecastillo.viper.photoslist.domain.model.Photo
import me.jorgecastillo.viper.photoslist.domain.model.PhotosPage
import java.io.IOException

class UnsplashPhotosDataSource(private val service: UnsplashService) : PhotosNetworkDataSource {

  override fun getAll(): Either<Error, List<Photo>> =
    Try {
      service.getPhotos().execute()
    }.fold(ifFailure = {
      Error.ServerError().left()
    }, ifSuccess = { response ->
      if (response.isSuccessful) {
        val headers = response.headers()
        PhotosPage(headers.get("X-Total")!!.toLong(),
            headers.get("X-Per-Page")!!.toInt(),
            response.body()!!)
      } else throw IOException("Coins not found.")
    })
}

@Throws(IOException::class)
fun getPhoto(id: String): PhotoDto {
  val response = service.getPhoto(id).execute()
  return if (response.isSuccessful) {
    response.body()!!
  } else throw IOException("Photo not found.")
}

companion object {
  const val MIN_REQUEST_TIME_MILLIS = 1500L
}
}
