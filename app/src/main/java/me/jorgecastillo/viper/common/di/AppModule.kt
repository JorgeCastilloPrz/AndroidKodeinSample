package me.jorgecastillo.viper.common.di

import android.content.Context
import me.jorgecastillo.viper.common.domain.repository.PhotosNetworkDataSource
import me.jorgecastillo.viper.common.data.network.UnsplashPhotosDataSource
import me.jorgecastillo.viper.common.data.network.http.httpClient
import me.jorgecastillo.viper.common.data.network.http.HeadersInterceptor
import me.jorgecastillo.viper.common.data.network.UnsplashService
import me.jorgecastillo.viper.common.data.network.http.loggingInterceptor
import me.jorgecastillo.viper.common.data.network.photosService
import me.jorgecastillo.viper.common.log.AndroidLogger
import me.jorgecastillo.viper.common.log.Logger
import me.jorgecastillo.viper.common.router.PhotoAppRouter
import me.jorgecastillo.viper.common.router.Router
import me.jorgecastillo.viper.photoslist.domain.repository.PhotosRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * Application scoped dependencies.
 */
fun appModule(appContext: Context) = Kodein.Module {
  bind<Context>() with provider { appContext }
  bind<Logger>() with singleton { AndroidLogger(instance()) }
  bind<Router>() with provider { PhotoAppRouter(instance()) }
  bind<Interceptor>(tag = "headers") with singleton { HeadersInterceptor() }
  bind<Interceptor>(tag = "logging") with singleton { loggingInterceptor() }
  bind<OkHttpClient>() with singleton {
    httpClient(instance(tag = "headers"), instance(tag = "logging"))
  }
  bind<UnsplashService>() with singleton { photosService(instance()) }
  bind<PhotosNetworkDataSource>() with singleton { UnsplashPhotosDataSource(instance()) }
  bind<PhotosRepository>() with singleton { PhotosRepository(instance()) }
}
