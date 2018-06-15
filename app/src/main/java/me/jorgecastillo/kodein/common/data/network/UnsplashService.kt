package me.jorgecastillo.kodein.common.data.network

import me.jorgecastillo.kodein.common.data.network.model.GetPhotosResponse
import me.jorgecastillo.kodein.common.data.network.model.PhotoDto
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

fun photosService(client: OkHttpClient): UnsplashService =
    Retrofit.Builder().baseUrl("https://api.unsplash.com")
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create()).build()
        .create(UnsplashService::class.java)

interface UnsplashService {

  companion object {
    const val ITEMS_PER_PAGE = 30
  }

  @GET("/photos")
  fun getPhotos(@Query("page") page: Int = 1,
                @Query("per_page") perPage: Int = ITEMS_PER_PAGE,
                @Query("orientation") orientation: String = "squarish")
      : Call<List<PhotoDto>>

  @GET("/search/photos")
  fun getPhotos(@Query("query") query: String,
                @Query("page") page: Int = 1,
                @Query("per_page") perPage: Int = ITEMS_PER_PAGE,
                @Query("orientation") orientation: String = "squarish")
      : Call<GetPhotosResponse>

  @GET("/photos/{id}")
  fun getPhoto(@Path("id") id: String): Call<PhotoDto>
}
