package me.jorgecastillo.kodein.common.di

import android.content.Context
import me.jorgecastillo.kodein.common.data.local.InMemoryPhotosDataSource
import me.jorgecastillo.kodein.common.data.network.UnsplashPhotosDataSource
import me.jorgecastillo.kodein.common.data.network.UnsplashService
import me.jorgecastillo.kodein.common.data.network.http.HeadersInterceptor
import me.jorgecastillo.kodein.common.data.network.http.httpClient
import me.jorgecastillo.kodein.common.data.network.http.loggingInterceptor
import me.jorgecastillo.kodein.common.domain.interactor.Invoker
import me.jorgecastillo.kodein.common.domain.interactor.UseCaseInvoker
import me.jorgecastillo.kodein.common.domain.repository.PhotosLocalDataSource
import me.jorgecastillo.kodein.common.domain.repository.PhotosNetworkDataSource
import me.jorgecastillo.kodein.common.log.AndroidLogger
import me.jorgecastillo.kodein.common.log.Logger
import me.jorgecastillo.kodein.photoslist.domain.repository.PhotosRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import org.kodein.di.generic.with
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Application scoped dependencies. Dependencies that we would need to reuse at any point in the
 * application lifecycle like singletons will be binded here.
 *
 * It brings into scope bindings defined in other app scoped modules for better modularity.
 */
fun appModule(appContext: Context) = Kodein.Module {
  bind<Context>() with provider { appContext }
  bind<Logger>() with singleton { AndroidLogger() }
  bind<Invoker>() with singleton { UseCaseInvoker() }

  import(httpAppModule())
  import(photosAppModule())
}

fun httpAppModule() = Kodein.Module {
  bind<Interceptor>(tag = "headers") with singleton { HeadersInterceptor() }
  bind<Interceptor>(tag = "logging") with singleton { loggingInterceptor() }
  bind<OkHttpClient>() with singleton {
    httpClient(instance(tag = "headers"), instance(tag = "logging"))
  }
}

fun photosAppModule() = Kodein.Module {
  bind<UnsplashService>() with singleton {
    Retrofit.Builder()
      .baseUrl("https://api.unsplash.com")
      .client(instance())
      .addConverterFactory(MoshiConverterFactory.create())
      .build()
      .create(UnsplashService::class.java)
  }

  constant(tag = "ttl") with TimeUnit.HOURS.toMillis(1)

  bind<PhotosLocalDataSource>() with singleton { InMemoryPhotosDataSource(instance(tag = "ttl")) }
  bind<PhotosNetworkDataSource>() with singleton { UnsplashPhotosDataSource(instance()) }
  bind<PhotosRepository>() with singleton { PhotosRepository(instance(), instance()) }
}
