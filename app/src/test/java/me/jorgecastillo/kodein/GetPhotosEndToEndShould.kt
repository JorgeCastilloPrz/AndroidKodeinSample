package me.jorgecastillo.kodein

import android.content.Context
import arrow.core.left
import arrow.core.right
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import me.jorgecastillo.kodein.common.data.network.PhotosNotFound
import me.jorgecastillo.kodein.common.di.appModule
import me.jorgecastillo.kodein.common.di.baseActivityModule
import me.jorgecastillo.kodein.common.domain.interactor.Invoker
import me.jorgecastillo.kodein.common.domain.model.Photo
import me.jorgecastillo.kodein.common.domain.repository.PhotosLocalDataSource
import me.jorgecastillo.kodein.common.domain.repository.PhotosNetworkDataSource
import me.jorgecastillo.kodein.common.router.Navigator
import me.jorgecastillo.kodein.photoslist.di.photoListActivityModule
import me.jorgecastillo.kodein.photoslist.domain.interactor.GetPhotos
import me.jorgecastillo.kodein.photoslist.domain.repository.PhotosRepository
import me.jorgecastillo.kodein.photoslist.presenter.PhotoListPresenter
import org.junit.Test
import org.junit.runner.RunWith
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * End to end test mocking out view implementation, threading and data source implementations. We
 * are testing all the resting layers on integration by providing injection of all the parts in
 * between both edges by using Kodein.
 */
@RunWith(MockitoJUnitRunner::class)
class GetPhotosEndToEndShould : KodeinAware {

  val presenter: PhotoListPresenter by instance()

  @Mock
  lateinit var context: Context

  @Mock
  lateinit var localDataSource: PhotosLocalDataSource
  @Mock
  lateinit var networkDataSource: PhotosNetworkDataSource
  @Mock
  lateinit var navigator: Navigator
  @Mock
  lateinit var view: PhotoListPresenter.View

  override val kodein = Kodein.lazy {
    // production modules
    import(appModule(context))
    import(photoListActivityModule())

    // overrides
    bind<Invoker>(overrides = true) with singleton { BlockingUseCaseInvoker() }
    bind<Navigator>(overrides = true) with singleton { navigator }
    bind<PhotosLocalDataSource>(overrides = true) with singleton { localDataSource }
    bind<PhotosNetworkDataSource>(overrides = true) with singleton { networkDataSource }
  }

  @Test
  fun showPhotosOnSuccessfulLoadingFromCache() {
    givenLocalDataSourcePhotos(anyPhotos)

    presenter.resume(view)

    verify(view).renderPhotos(anyPhotos)
  }

  @Test
  fun showPhotosWhenNotAvailableOnCacheButOnNetwork() {
    givenNoPhotosCached()
    givenNetworkDataSourcePhotos(anyPhotos)

    presenter.resume(view)

    verify(view).renderPhotos(anyPhotos)
  }

  @Test
  fun givesFeedbackWhenLoadingPhotosError() {
    givenNoPhotosAvailableOnAnySources()

    presenter.resume(view)

    verify(view).displayLoadingPhotosError()
  }

  private fun givenLocalDataSourcePhotos(anyPhotos: List<Photo>) {
    whenever(localDataSource.getAll()).thenReturn(anyPhotos.right())
  }

  private fun givenNetworkDataSourcePhotos(anyPhotos: List<Photo>) {
    whenever(networkDataSource.getAll()).thenReturn(anyPhotos.right())
  }

  private fun givenNoPhotosCached() {
    whenever(localDataSource.getAll()).thenReturn(PhotosNotFound().left())
  }

  private fun givenNoPhotosAvailableOnAnySources() {
    whenever(localDataSource.getAll()).thenReturn(PhotosNotFound().left())
    whenever(networkDataSource.getAll()).thenReturn(PhotosNotFound().left())
  }

  companion object {
    val anyPhotos: List<Photo> = (1..3).map {
      Photo(it.toString(),
          "http:url/$it",
          "AuthorName$it",
          "Description$it",
          "2018-06-16T08:52:46-0500")
    }
  }
}
