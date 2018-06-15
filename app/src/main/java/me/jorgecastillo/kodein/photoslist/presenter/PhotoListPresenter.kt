package me.jorgecastillo.kodein.photoslist.presenter

import arrow.core.Either
import me.jorgecastillo.kodein.common.domain.error.Error
import me.jorgecastillo.kodein.common.presenter.BasePresenter
import me.jorgecastillo.kodein.common.router.Navigator
import me.jorgecastillo.kodein.photoslist.domain.interactor.GetPhotos
import me.jorgecastillo.kodein.common.domain.model.Photo

class PhotoListPresenter(private val getPhotos: GetPhotos, private val navigator: Navigator) :
    BasePresenter<PhotoListPresenter.View>() {

  interface View : BasePresenter.View {
    fun renderPhotos(photos: List<Photo>)
    fun displayLoadingPhotosError()
  }

  override fun resume(view: View) {
    super.resume(view)
    loadPhotos()
  }

  private fun loadPhotos() {
    val params = GetPhotos.Params(page = 1, query = "landscape")
    getPhotos.execute(params, ::onPhotosArrived)
  }

  private fun onPhotosArrived(result: Either<Error, List<Photo>>) {
    result.fold(ifLeft = {
      view?.displayLoadingPhotosError()
    }, ifRight = {
      view?.renderPhotos(it)
    })
  }

  fun onPhotoClick(photoId: String) {
    navigator.goToDetail(photoId)
  }
}
